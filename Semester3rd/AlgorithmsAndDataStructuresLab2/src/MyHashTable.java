/**
 * Created by alesia on 9/26/17.
 */
public class MyHashTable {
    private Triangle [] triangles;
    private boolean [] isDeleted;
    private int size = 0;

    public MyHashTable (int size) {
        this.triangles = new Triangle[size];
        this.isDeleted = new boolean[size];
    }

    public Triangle[] getTriangles () {
        return this.triangles;
    }

    public boolean hashInsert (Triangle triangle) throws Exception {
        if(triangles.length == size) throw new Exception("Hash table \"MyHashTable\" is full.");

        int index = hashFunctionMultiple(triangle);

        int counter = 0;
        while (!indexIsFree(index) || counter == triangles.length - 1) {
            if(index == triangles.length - 1) {
                index = 0;
            } else {
                index++;
            }
            counter++;
        }
        triangles[index] = triangle;
        size++;
        return true;
    }

    public int hashSearch (Triangle triangle) {
        int index = -1;
        int key = hashFunctionMultiple(triangle);

        if (triangles[key] == null && !isDeleted[key]) return index; //no such Triangle

        int counter = 0;
        for (int i = key; i < triangles.length; i++) {
            if(triangle.equals(triangles[i])) return i;
            if(i == triangles.length - 1 && counter <= triangles.length - 1) i = -1;
            counter++;
        }
        return index;
    }

    public void hashDelete (int i) {
        if(triangles[i] != null) {
            triangles[i] = null;
            isDeleted[i] = true;
            size--;
        }
    }

    private boolean indexIsFree (int i) {
        return triangles[i] == null;
    }

    private int hashFunctionMultiple (Triangle triangle) {
        int k = (int) triangle.calculateArea();
        final double A = (Math.sqrt(5) - 1) / 2;
        double index = triangles.length * ((k*A)%1);

        return  (int) index;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < triangles.length; i++) {
            if(triangles[i] == null) {
                str += String.format("Position = %1$-5d Triangle = %2$-5s \r\n", i, triangles[i]);
            } else {
                int key = hashFunctionMultiple(triangles[i]);
                str += String.format("Position = %1$-5d Key = %2$-5d %3$-5s \r\n", i, key, triangles[i]);
            }
        }
        return str;
    }
}
