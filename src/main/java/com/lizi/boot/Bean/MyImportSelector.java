package com.lizi.boot.Bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    /***
     * 返回值就是导入到组件中的全类名
     * 返回需要导入的组件的全类名的数组
     * @param annotationMetadata 当前标注import注解的类的所有注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 不可返回 null
        return new String[]{"com.lizi.boot.Bean.Person"};
    }
}
