package org.hzcu;

import com.google.gson.annotations.SerializedName;
import javassist.*;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
        // 这里可以添加你的代码
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("C:\\Users\\Administrator\\Desktop\\instrumented-MyBatisCodeHelper-Pro241-3.3.3+2321.jar");
        CtClass cc = pool.get("com.ccnode.codegenerator.af.d.f");
        CtMethod[] ctMethods = cc.getDeclaredMethods("a");
        CtField[] declaredFields = cc.getDeclaredFields();
        for (CtField declaredField : declaredFields) {
            SerializedName annotation = (SerializedName)declaredField.getAnnotation(SerializedName.class);
            if (annotation.value().equals("valid")){
// 获取valid的set方法的参数：Boolean
                CtClass[] params1 = new CtClass[] { pool.get("java.lang.Boolean") };
// 获取Valid的set方法
                CtMethod setValidMethod = cc.getDeclaredMethod("a", params1);
// 获取valid的get方法
                CtMethod getValidMethod = null;
                for (CtMethod ctMethod : ctMethods) {
//通过返回值确定get方法
                    if(ctMethod.getReturnType().equals(pool.get("java.lang.Boolean"))){
                        getValidMethod = ctMethod;
                        break;
                    }
                }
// 修改valid的set方法
// 设为True
                getValidMethod.setBody("{return Boolean.TRUE;}");
// 修改valid的get方法
// 直接返回True
                setValidMethod.insertBefore("{$1 = Boolean.TRUE;}");
            }
            if (annotation.value().equals("validTo")){
// 获取validTo的get方法
                CtMethod getValidToMethod = null;
// 获取validTo的set方法的参数：Long
                CtClass[] params = new CtClass[] { pool.get("java.lang.Long") };
                for (CtMethod ctMethod : ctMethods) {
//通过返回值确定get方法
                    if(ctMethod.getReturnType().equals(pool.get("java.lang.Long"))){
                        getValidToMethod = ctMethod;
                        break;
                    }
                }
// 获取validTo的set方法
                CtMethod setValidToMethod = cc.getDeclaredMethod("a", params);
// 修改validTo的get方法
                assert getValidToMethod != null;
                setValidToMethod.insertBefore("$1 = Long.valueOf(4078257911000L);");
                getValidToMethod.setBody("{return Long.valueOf(4078257911000L);}");
            }
        }
// 将修改后的Class b写入指定文件夹
        cc.writeFile("C:\\Users\\Administrator\\Desktop\\");
    }
}
