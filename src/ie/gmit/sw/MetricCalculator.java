package ie.gmit.sw;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.util.jar.*;

/**
 * The metricCalculator Class
 * Calculates stability and handles reading of the JAR file.
 */

public class MetricCalculator {

	private HashMap<String, Metric> metricsMap = new HashMap<>();
    private String jarName;
    
    /**
     * Calculates the stability for the selected jar file
     *
     * @param jarName
     * Sets the file path of a .jar file to a String.
     */
    public MetricCalculator(String jarName) {
		this.jarName = jarName;
		addClass();
		calculateMetric();
	}//- End of MetricCalculator
    
    public Object[][] getMetricData(){
        int i = 0;
        Object[][] data = new Object[metricsMap.size()][4];

        // for each metric object in the map
        for(Metric m : metricsMap.values()){
            //- Add data to the second array
            data[i][0] = m.getClsName();  
            data[i][1] = m.getStability();  
            data[i][2] = m.getOutDegree();  
            data[i][3] = m.getInDegree();   

            //- Counter
            i++;
        }//- End of for

        return data;
    } // getMetricData()
    
    private void addClass(){
        int i = 0;

        try {
            //- Code from Dynamic Class Introspection.
    		JarInputStream in = new JarInputStream(new FileInputStream(new File(jarName)));
    		JarEntry next = in.getNextJarEntry();
    		
    		while(next != null) {
    			if(next.getName().endsWith(".class")){
    				String name = next.getName().replaceAll("/", "\\.");
    				name = name.replaceAll(".class", "");
    				if(!name.contains("$")) name.substring(0, name.length()-".class".length());
    				//System.out.println(name);
    				//- Add class name to hashmap
    				metricsMap.put(name, new Metric());
    				metricsMap.get(name).setClsName(name);
    				//- Number of classes loaded are counted here.
    				i++;
    			}//- End of if
    			next = in.getNextJarEntry();
    		}//- End of while

            System.out.println(i + " classes loaded.");
            System.out.println("Map size: " + metricsMap.size());
        } catch (Exception e){
            e.printStackTrace();
        }//- End of try/catch
    }//- End of addClass()    

    private void calculateMetric(){

        try {
            //- Declaring variable for JAR file.
            File file = new File(jarName);

            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            //- TheClassLoader loads classes from the JAR file.
            ClassLoader clsLoader = new URLClassLoader(urls);

            for (String clsName : metricsMap.keySet()) {
                Class cls = Class.forName(clsName, false, clsLoader);

                // analyse class to calculate in and out degree
                processClass(cls);
            }//- End of for
        } catch (Exception e){
            e.printStackTrace();
        }//- End of try/catch
    }//- End of calculateMetric()
    
    private void processClass(Class cls){
        int outDegree = 0;
        boolean interfaced = cls.isInterface(); 
        
        //- Get the set of interface the class implements.
        Class[] interfaces = cls.getInterfaces();

        for(Class i : interfaces){
            if(metricsMap.containsKey(i.getName())) {
                //- outDegree +1.
                outDegree++;

                //- inDegree +1.
                Metric m = metricsMap.get(i.getName());
                m.setInDegree(m.getInDegree() + 1);
            }//- End of if
        }//- End of for

        Constructor[] construct = cls.getConstructors();
        Class[] constructorParams;

        //- Get parameters for constructors.
        for(Constructor c : construct){
            constructorParams = c.getParameterTypes();
            for(Class param : constructorParams){
                if(metricsMap.containsKey(param.getName())){
                    //- outDegree +1.
                    outDegree++;

                    //- inDegree +1.
                    Metric m = metricsMap.get(param.getName());
                    m.setInDegree(m.getInDegree() + 1);
                }//- End of if
            }//- End of inner for
        }//- End of outer for

        Field[] fields = cls.getFields();
        for(Field f : fields){
            if(metricsMap.containsKey(f.getName())){
                //- outDegree +1.
                outDegree++;

                //- inDegree +1.
                Metric m = metricsMap.get(f.getName());
                m.setInDegree(m.getInDegree() + 1);
            }//- End of if
        }//- End of for

        Method[] methods = cls.getMethods();
        Class[] methodParams;
        for(Method m : methods){
        	Class methodReturnType = m.getReturnType();
            if(metricsMap.containsKey(methodReturnType.getName())){
                //- outDegree +1.
                outDegree++;

                //- inDegree +1.
                Metric metric = metricsMap.get(methodReturnType.getName());
                metric.setInDegree(metric.getInDegree() + 1);
            }//- End of if

            methodParams = m.getParameterTypes();
            for(Class mp : methodParams){
            	if(metricsMap.containsKey(mp.getName())){
            		//- outDegree +1.
                    outDegree++;

                    //- inDegree +1;
                    Metric metric = metricsMap.get(mp.getName());
                    metric.setInDegree(metric.getInDegree() + 1);
                }//- End of if
            }//- End of inner for
        }//- End of outer for
        metricsMap.get(cls.getName()).setOutDegree(outDegree);
    }//- End of processClass()  
}//- End of MetricCalculator
