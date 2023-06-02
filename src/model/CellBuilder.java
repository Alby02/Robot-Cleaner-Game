package model;

import java.lang.reflect.InvocationTargetException;

public class CellBuilder {
    /*URLClassLoader child = new URLClassLoader(
            new URL[] {myJar.toURI().toURL()},
            this.getClass().getClassLoader()
    );
    Class classToLoad = Class.forName("com.MyClass", true, child);
    Method method = classToLoad.getDeclaredMethod("myMethod");
    Object instance = classToLoad.newInstance();
    Object result = method.invoke(instance);aaaaaaaaaaaaaaaa */

    public static Cell create(String[] el, int l,int  i, int j)
    {
        Cell c = null;
        if(!(l == 0 || l == 0))
        {
            try {
                c  = (Cell) Class.forName("model.element."+el[l]).getConstructor(int.class, int.class, int.class).newInstance(i,j,l);
            }
            catch (InstantiationException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch (SecurityException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch(Exception e){
                e.printStackTrace();
                System.exit(-100);
            } //TODO Sistemare i catch
        }
        return c;
    }

    /*private static Cell randCasella(int i, int j) {
        double randomNum = Math.random();
        randomNum = Math.round(randomNum * 100) / 1000;

        if(randomNum < 0.1) {
            return new Sink(i, j);
        }
        else if(randomNum < 0.2) {
            return new Washer(i, j);
        }
        else if(randomNum < 0.3) {
            return new Oven(i, j);
        }
        else{
            return null;
        }
    }*/
}
