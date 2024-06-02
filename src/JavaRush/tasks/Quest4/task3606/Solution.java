package JavaRush.tasks.Quest4.task3606;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("\\.","/") + "/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        ClassLoader classLoader = Solution.class.getClassLoader();
        File dir = new File(packageName);
        String[] pathes = dir.list((dir1, name) -> name.endsWith(".class"));
        String className;
        for (String fileName : pathes) {
            className=packageName.replaceAll("[/\\\\]",".").substring(packageName.lastIndexOf("JavaRush"))+"."+fileName.substring(0,fileName.length()-6);
            Class<?> aClass = classLoader.loadClass(className);
            hiddenClasses.add(aClass);
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        String lowerCaseKey = key.toLowerCase();
        try {
            Class resultClass=null;
            for (Class hiddenClass : hiddenClasses) {
                if(hiddenClass.getSimpleName().toLowerCase().equals(lowerCaseKey)){
                    resultClass = hiddenClass;
                    Constructor<?> declaredConstructor = resultClass.getDeclaredConstructor();
                    declaredConstructor.setAccessible(true);
                    return (HiddenClass) declaredConstructor.newInstance();
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}

