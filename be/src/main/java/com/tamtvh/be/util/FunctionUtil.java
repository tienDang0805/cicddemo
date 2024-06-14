package com.tamtvh.be.util;

import java.lang.reflect.Type;
import java.util.stream.Stream;

@SuppressWarnings({"rawtypes", "unused"})
public class FunctionUtil {

    private static final String PACKAGE_MODEL = "com.tamtvh.be.model.";
    private static final String PACKAGE_REPOSITORY = "com.tamtvh.be.repository.";
    private static final String SUFFIX_REPOSTIORY = "Repository";

    public static Class getModelClassFromRepo(final Type[] types) {
        try {
            var model = Stream.of(types.clone())
                    .filter(type ->  type.getTypeName().indexOf("com.tamtvh.be.repository") >= 0)
                    .map(type -> {
                        return PACKAGE_MODEL + type.getTypeName().replaceAll(SUFFIX_REPOSTIORY, "").replaceAll(PACKAGE_REPOSITORY, "");
                    }).findFirst().orElseThrow(() -> new InternalError("Can not found model entity"));
            return  Class.forName(model);
        } catch (ClassNotFoundException e) {
            throw new InternalError("Can not found model entity");
        }
    }
}
