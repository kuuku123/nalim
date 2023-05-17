package one.nalim;

import jdk.jfr.AnnotationElement;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

final public class AnnotationUtil {

    private static final int MAX_SCORE = 3;

    static Library findLibrary(AnnotatedElement element, Os expectedOs, Arch expectedArch) {

        return find(
                Library.class,
                element.getAnnotation(LibrarySet.class),
                element.getAnnotation(Library.class),
                LibrarySet::value ,
                 Library::os, Library::arch , expectedOs,expectedArch);
    }

    static Code findCode(Method method, Os expectedOs, Arch expectedArch) {
        return find(
                Code.class,
                method.getAnnotation(CodeSet.class),
                method.getAnnotation(Code.class),
                CodeSet::value, Code::os, Code::arch,
                expectedOs,expectedArch
        );
    }


    private static <T extends Annotation, U extends Annotation> U find(
            Class<U> annotationType,
            T containerAnnotations,
            U annotation,
            Function<T, U[]> annotationExtractor,
            Function<U,Os> osExtractor , Function<U, Arch> archExtractor,
            Os expectedOs, Arch expectedArch) {

        final U[] annotations;
        if (containerAnnotations == null ) {
            if (annotation == null) {
                return null;
            }

            annotations = (U[]) Array.newInstance(annotationType,1);
            annotations[0] = annotation;
        } else {
            annotations = annotationExtractor.apply(containerAnnotations);
            if (annotations == null || annotations.length == 0) {
                return null;
            }
        }


        U match = null;
        int matchScore = -1;
        for (int i = 0; i< annotations.length; i++) {
            final U a = annotations[i];
            final int score = score(expectedOs,expectedArch,osExtractor.apply(a),archExtractor.apply(a));
            if (score > matchScore) {
                match = a;
                matchScore = score;
                if (score >= MAX_SCORE) {
                    break;
                }
            }
        }
        return match;
    }
    private final static class AnnotationComparator<T extends Annotation> implements Comparator<T> {

        private final Os exepectedOs;
        private final Arch expectedArch;
        private final Function<T, Os> osExTractor;
        private final Function<T, Arch> archExtractor;

        AnnotationComparator(
                Os exepectedOs, Arch expectedArch,
                Function<T,Os> osExtractor, Function<T,Arch> archExtractor) {
            this.exepectedOs = exepectedOs;
            this.expectedArch = expectedArch;
            this.osExTractor = osExtractor;
            this.archExtractor = archExtractor;
        }

        @Override
        public int compare(T a, T b) {
            final Os aOs = osExTractor.apply(a);
            final Arch aArch = archExtractor.apply(a);
            final Os bOs= osExTractor.apply(b);
            final Arch bArch = archExtractor.apply(b);


            return score(exepectedOs,expectedArch,aOs,aArch) -
                    score(exepectedOs,expectedArch,bOs,bArch);
        }
    }


    private static <T extends  Annotation>Stream<T> stream(T[] array) {
        if (array == null) {
            return Stream.empty();
        } else {
            return Arrays.stream(array);
        }
    }

    private static int score(Os expectedOs, Arch expectedArch, Os os , Arch arch) {
        if (os == expectedOs) {
            if (arch == expectedArch) {
                return MAX_SCORE;
            }
            if (arch == Arch.UNSPECIFIED) {
                return 2;
            }
            return -1;
        }

        if (os == Os.UNSPECIFIED) {
            if(arch == expectedArch) {
                return 1;
            }
            if (arch == Arch.UNSPECIFIED) {
                return 0;
            }
            return -1;
        }

        return -1;
    }

    private AnnotationUtil() {}

}
