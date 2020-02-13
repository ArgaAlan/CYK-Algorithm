import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AlgoritmoCYK {

  public static ArrayList<String> generadores = new ArrayList<>();
  public static ArrayList<ArrayList<String>> resultados = new ArrayList<>();
  public static ArrayList<String> arbol = new ArrayList<>();
  public static String cadena /* = "baaba" */;
  public static ArrayList<CYK>[][] CYKt;

  public static void creacionCYK(String cadena, String path) {
    // Creacion tabla CYK
    CYKt = new ArrayList[cadena.length()][cadena.length()];

    // Poner la cadena en la tabla
    for (int i = 0; i < CYKt.length; i++) {
      CYKt[i][i] = new ArrayList<>(); // new CYK();
      /*
       * CYK c = new CYK(); CYKt[i][i].add(c); CYKt[i][i].get(0).value =
       * busquedaLista(Character.toString(cadena.charAt(i)));
       */ // Busco en la tabla
      // el generador que me
      // puede dar el
      // terminal y lo
      // agrego
      int[] a = {};
      int[] b = {};
      busquedaLista(Character.toString(cadena.charAt(i)), i, i, -1, -1, a, b);
      ArrayList<CYK>[][] CYKu = CYKt;
      int o = 0;
    }

    // Creacion de segunda diagonal, primeros generadores
    for (int i = 0; i < CYKt.length - 1; i++) {

      // REPETIR EL PROCESO CON CADA VALOR DE LA LISTA CON UN FOR N2

      CYKt[i][i + 1] = new ArrayList<>();
      for (int x = 0; x < CYKt[i][i].size(); x++) {
        for (int y = 0; y < CYKt[i + 1][i + 1].size(); y++) {
          String terminalesUnidos = CYKt[i][i].get(x).value + CYKt[i + 1][i + 1].get(y).value;
          int[] a = {i, i};
          int[] b = {i + 1, i + 1};
          busquedaLista(terminalesUnidos, i, i + 1, x, y, a, b);
          ArrayList<CYK>[][] CYKu = CYKt;
          int o = 0;
        }
      }
      // Uno de los index uno
      // a la izquierda y
      // otro a la de abajo
      // CYKt[i][i + 1].value = busquedaLista(terminalesUnidos); // Busco en la tabla
      // el generador que me
      // puede dar el
      // otro generador y lo
      // agrego
      // CYKt[i][i + 1].saleDe[0] = CYKt[i][i];
      // CYKt[i][i + 1].saleDe[1] = CYKt[i + 1][i + 1];
    }

    // Comenzar con la creacion de la tabla
    // i es el renglon y j es la columna



    for (int k = 2; k < CYKt.length; k++) {
      int j = k;
      for (int i = 0; j < CYKt.length; i++) {
        // CYKt[i][j] = celdaCYK(i, j);
        ArrayList<CYK>[][] CYKi = CYKt;
        int p = 0;
        celdaCYK(i, j);
        ArrayList<CYK>[][] CYKu = CYKt;
        int o = 0;
        j++;
      }
    }

    /*
     * for (int i = 0; i < CYKt.length; i++) { for (int j = 0; j < CYKt[i].length; j++) {
     * System.out.print("["); try { // System.out.print(CYKt[i][j].value + ","); for (int h = 0; h <
     * CYKt[i][j].size(); h++) { System.out.print(CYKt[i][j].get(h).value + ","); } } catch
     * (Exception e) { System.out.print("*" + ","); } System.out.print("]"); } System.out.println();
     * }
     */

    /*
     * System.out.println(CYKt[0][CYKt.length - 1].value + "0" + "->" + CYKt[0][CYKt.length -
     * 1].saleDe[0].value + "1"); System.out.println(CYKt[0][CYKt.length - 1].value + "0" + "->" +
     * CYKt[0][CYKt.length - 1].saleDe[1].value + "1");
     */

    // Elimina ceros
    for (int i = 0; i < CYKt.length; i++) {
      for (int j = 0; j < CYKt[i].length; j++) {
        try {
          if (CYKt[i][j].size() > 1) {
            for (int h = 0; h < CYKt[i][j].size(); h++) {
              if (CYKt[i][j].get(h).value == "0") {
                CYKt[i][j].remove(h);
              }
            }
          }
        } catch (Exception e) {

        }
      }
    }

    for (int i = 0; i < CYKt.length; i++) {
      for (int j = 0; j < CYKt[i].length; j++) {
        System.out.print("[");
        try {
          // System.out.print(CYKt[i][j].value + ",");
          ArrayList<String> impresos = new ArrayList<>();
          for (int h = 0; h < CYKt[i][j].size(); h++) {
            if (!impresos.contains(CYKt[i][j].get(h).value)) {
              System.out.print(CYKt[i][j].get(h).value + ",");
              impresos.add(CYKt[i][j].get(h).value);
            }

          }
        } catch (Exception e) {
          System.out.print("*" + ",");
        }
        System.out.print("]");
      }
      System.out.println();
    }

    System.out.println();

    try {
      int x = -1;
      for (int i = 0; i < CYKt[0][CYKt.length - 1].size(); i++) {
        if (CYKt[0][CYKt.length - 1].get(i).value.equals("S")) {
          x = i;
          break;
        }
      }
      if (x > -1) {
        System.out.println("La cadena SÍ PERTENECE a la gramatica");
        creacionDeArbol(CYKt[0][CYKt.length - 1].get(x), 0, 0);
      } else {
        System.out.println("La cadena NO PERTENECE a la gramatica");
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

    /*
     * for (int i = 0; i < arbol.size(); i++) { for (int j = 0; j < arbol.get(i).size(); j++) {
     * System.out.print(arbol.get(i).get(j)); } System.out.println(); }
     */

    try {
      PrintWriter writer = new PrintWriter(path);
      writer.println("digraph{");
      for (int i = 0; i < arbol.size(); i++) {
        writer.println(arbol.get(i));
      }
      writer.println("}");
      writer.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    /*
     * for (int i = 0; i < arbol.size() - 1; i++) { for (int j = i + 1; j < arbol.size(); j++) { if
     * (arbol.get(i).compareTo(arbol.get(j)) > 0) { String temp = arbol.get(i); arbol.set(i,
     * arbol.get(j)); arbol.set(j, temp); } } }
     * 
     * System.out.println();
     * System.out.println("////////////////////////////////////////////////////////////////////");
     * System.out.println();
     * 
     * for (int i = 0; i < arbol.size(); i++) { System.out.println(arbol.get(i)); }
     */
  }

  public static void creacionDeArbol(CYK cyk, int level, int pos) {

    if (cyk.saleDe[0] != null && cyk.saleDe[1] != null) {
      if (cyk.saleDe[0].value == cyk.saleDe[1].value) {
        arbol.add(cyk.value + Integer.toString(level) + "[label=\"" + cyk.value + "\"]");
        arbol.add(cyk.saleDe[0].value + Integer.toString(pos) + Integer.toString(level + 1)
            + "[label=\"" + cyk.saleDe[0] + "\"]");
        arbol.add(cyk.saleDe[1].value + Integer.toString(pos + 1) + Integer.toString(level + 1)
            + "[label=\"" + cyk.saleDe[1] + "\"]");
        arbol.add(cyk.value + Integer.toString(level) + "->" + cyk.saleDe[0].value
            + Integer.toString(pos) + Integer.toString(level + 1));
        arbol.add(cyk.value + Integer.toString(level) + "->" + cyk.saleDe[1].value
            + Integer.toString(pos + 1) + Integer.toString(level + 1));

        cyk.saleDe[0].value = cyk.saleDe[0].value + Integer.toString(pos);
        cyk.saleDe[1].value = cyk.saleDe[1].value + Integer.toString(pos + 1);

        creacionDeArbol(cyk.saleDe[0], level + 1, 0);
        creacionDeArbol(cyk.saleDe[1], level + 1, 2);

      } else {
        arbol.add(cyk.value + Integer.toString(level) + "[label=\"" + cyk.value + "\"]");
        arbol.add(cyk.saleDe[0].value + Integer.toString(pos) + Integer.toString(level + 1)
            + "[label=\"" + cyk.saleDe[0] + "\"]");
        arbol.add(cyk.saleDe[1].value + Integer.toString(pos + 1) + Integer.toString(level + 1)
            + "[label=\"" + cyk.saleDe[1] + "\"]");
        arbol.add(cyk.value + Integer.toString(level) + "->" + cyk.saleDe[0].value
            + Integer.toString(pos) + Integer.toString(level + 1));
        arbol.add(cyk.value + Integer.toString(level) + "->" + cyk.saleDe[1].value
            + Integer.toString(pos + 1) + Integer.toString(level + 1));

        cyk.saleDe[0].value = cyk.saleDe[0].value + Integer.toString(pos);
        cyk.saleDe[1].value = cyk.saleDe[1].value + Integer.toString(pos + 1);

        creacionDeArbol(cyk.saleDe[0], level + 1, 0);
        creacionDeArbol(cyk.saleDe[1], level + 1, 2);
      }


    } else if (cyk.saleDe[0] != null) {

      arbol.add(cyk.value + Integer.toString(level) + "[label=\"" + cyk.value + "\"]");
      arbol.add(cyk.saleDe[0].value + Integer.toString(pos) + Integer.toString(level + 1)
          + "[label=\"" + cyk.saleDe[0] + "\"]");

      arbol.add(cyk.value + Integer.toString(level) + "->" + cyk.saleDe[0].value
          + Integer.toString(pos) + Integer.toString(level + 1));

      cyk.saleDe[0].value = cyk.saleDe[0].value + Integer.toString(pos);

      creacionDeArbol(cyk.saleDe[0], level + 1, 0);

    } else if (cyk.saleDe[1] != null) {

      arbol.add(cyk.value + Integer.toString(level) + "[label=\"" + cyk.value + "\"]");
      arbol.add(cyk.saleDe[1].value + Integer.toString(pos + 1) + Integer.toString(level + 1)
          + "[label=\"" + cyk.saleDe[1] + "\"]");

      arbol.add(cyk.value + Integer.toString(level) + "->" + cyk.saleDe[1].value
          + Integer.toString(pos + 1) + Integer.toString(level + 1));

      cyk.saleDe[1].value = cyk.saleDe[1].value + Integer.toString(pos + 1);

      creacionDeArbol(cyk.saleDe[1], level + 1, 2);

    }
  }

  public static void celdaCYK(int i, int j) {
    int h = i + 1;
    for (int u = i; u < j; u++) {

      for (int x = 0; x < CYKt[i][u].size(); x++) {
        for (int y = 0; y < CYKt[h][j].size(); y++) {
          ArrayList<CYK>[][] CYKi = CYKt;
          int p = 0;
          String str = CYKt[i][u].get(x).value + CYKt[h][j].get(y).value;
          int[] a = {i, u};
          int[] b = {h, j};
          busquedaLista(str, i, j, x, y, a, b);

        }
      }
      h++;

      /*
       * String str = CYKt[i][u].value + CYKt[h][j].value; if (busquedaLista(str) != "0") { CYK c =
       * new CYK(); c.value = busquedaLista(str); c.saleDe[0] = CYKt[i][u]; c.saleDe[1] =
       * CYKt[h][j]; return c; } h++;
       */
    }
    /*
     * CYK c = new CYK(); return c;
     */
  }

  public static void busquedaLista(String str, int a, int b, int saleDe0, int saleDe1,
      int[] posSaleDe0, int[] posSaleDe1) {
    for (int i = 0; i < resultados.size(); i++) {
      for (int j = 0; j < resultados.get(i).size(); j++) {
        if (resultados.get(i).get(j).equals(str)) {
          CYK c = new CYK();
          c.value = generadores.get(i);
          if (str.equals(str.toLowerCase())) {
            c.terminal = str;
          }
          if (saleDe0 > -1) {
            c.saleDe[0] = CYKt[posSaleDe0[0]][posSaleDe0[1]].get(saleDe0);
            c.saleDe[1] = CYKt[posSaleDe1[0]][posSaleDe1[1]].get(saleDe1);
          }
          try {
            CYKt[a][b].add(c);
          } catch (Exception e) {
            // TODO: handle exception
            CYKt[a][b] = new ArrayList<>();
            CYKt[a][b].add(c);
          }

        }
      }
    }
    try {
      if (CYKt[a][b].size() == 0) {
        CYK c = new CYK();
        c.value = "0";
        CYKt[a][b].add(c);
      }
    } catch (Exception e) {
      CYKt[a][b] = new ArrayList<>();
      if (CYKt[a][b].size() == 0) {
        CYK c = new CYK();
        c.value = "0";
        CYKt[a][b].add(c);
      }
    }

  }



  public static void agregaLista(String str) {
    String[] g = str.split("-");
    generadores.add(g[0]);
    String[] t = g[1].split("/");
    resultados.add(new ArrayList<>());
    for (int i = 0; i < t.length; i++) {
      resultados.get(resultados.size() - 1).add(t[i]);
    }
  }

  public static void main(String[] args) {

    // agregaLista("S-AB/SS/AC/BD/BA");
    // agregaLista("A-a");
    // agregaLista("B-b");
    // agregaLista("C-SB");
    // agregaLista("D-SA");
    String path = JOptionPane.showInputDialog("Agregue el path del archivo txt");

    File file = new File(path);

    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(file));
      String st;
      try {
        while ((st = br.readLine()) != null) {
          agregaLista(st);
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    /*
     * agregaLista("S-AB/BC"); agregaLista("A-BA/a"); agregaLista("B-CC/b"); agregaLista("C-AB/a");
     */
    // cadena = "baaba";

    cadena = JOptionPane.showInputDialog("Escriba su cadena");

    // path = JOptionPane.showInputDialog("Escriba el nombre para el archivo txt de los
    // resultados");
    path = JOptionPane.showInputDialog("Escriba el nombre para el archivo del arbol de derivacion");
    // C:\Users\ArgaA\Desktop\gramatica.txt
    creacionCYK(cadena, path);
  }

}


class CYK {
  String value;
  CYK[] saleDe;
  String terminal;

  public CYK() {
    this.value = "0";
    this.saleDe = new CYK[2];
    this.saleDe[0] = null;
    this.saleDe[1] = null;
    this.terminal = (String) null;
  }

  public String toString() {
    return this.value;
  }
}
