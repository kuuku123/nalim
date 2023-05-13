package one.nalim;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

final public class AnnotationUtil {

    static Library findLibrary(AnnotatedElement element, Os os, Arch arch) {
        LibrarySet set = element.getAnnotation(LibrarySet.class);
        if (set == null) {
            return null;
        }
        Library[] libraries = set.value();
        if (libraries == null) {
            return null;
        }

        
    }

    static Code findCode(AnnotatedElement element, Os os, Arch arch) {
        CodeSet set = element.getAnnotation(CodeSet.class);
        if (set == null) {
            return null;
        }
        Code[] codes= set.value();
        if (codes == null) {
            return null;
        }
    }

    static Link findLink(AnnotatedElement element, Os os, Arch arch) {
        LinkSet set = element.getAnnotation(LinkSet.class);
        if (set == null) {
            return null;
        }
        Link[] links= set.value();
        if (links == null) {
            return null;
        }
    }
    private AnnotationUtil() {}

}
