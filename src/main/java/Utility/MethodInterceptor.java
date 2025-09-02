package Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import Base.TestBase;

public class MethodInterceptor extends TestBase implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

        List<Map<String, String>> list = ExcelUtils.getTestDetails("RUN_MANAGER");
        List<IMethodInstance> result = new ArrayList<>();

        for (IMethodInstance method : methods) {
            String methodName = method.getMethod().getMethodName();

            for (Map<String, String> row : list) {
                String excelTestName = row.get("Test Name");
                String executeFlag   = row.get("Execute");
                String group         = row.get("Group");
                String description   = row.get("Test Description");

              

             
                if (methodName.equalsIgnoreCase(excelTestName)) {

                    // Only run if Execute = Yes
                    if ("Yes".equalsIgnoreCase(executeFlag != null ? executeFlag.trim() : "")) {
                        method.getMethod().setDescription(description);

                        try {
                            String expectedGroup = PropertiesReader.readPropertiesFile("Group");
                            if (expectedGroup == null) expectedGroup = "";
                            expectedGroup = expectedGroup.trim();

                          
                            if (expectedGroup.isEmpty()) {
                                result.add(method);
                                System.out.println(">>> Added (no group in config): " + methodName);
                            }
                            
                            else if (group == null || group.trim().isEmpty()) {
                                result.add(method);
                                System.out.println(">>> Added (no group in Excel): " + methodName);
                            }
                      
                            else {
                                String[] excelGroups = group.split(",");
                                for (String g : excelGroups) {
                                    if (g.trim().equalsIgnoreCase(expectedGroup)) {
                                        result.add(method);
                                        System.out.println(">>> Added (group matched): " + methodName);
                                        break;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return result;
    }
}
