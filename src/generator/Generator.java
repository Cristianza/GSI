/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author pc
 */
public class Generator {

    /**
     * @param args the command line arguments
     */
    public static BigInteger m = new BigInteger("0");
    public static BigInteger n = new BigInteger("0");

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int coc;
        File file = new File("archivo.txt");
        int mn = 0, mn1, mn2, mn3, mn4, mn5;
        Hashtable<String, Registro> hash = new Hashtable<String, Registro>();
        System.out.println("1) Cargar el archivo");
        System.out.println("2) Crear el archivo");
        coc = sc.nextInt();
        if (coc == 1) {
            cargar_txt(file, hash);
        } else {
            String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            System.out.println("Numero de Registros: ");
            n = sc.nextBigInteger();
            System.out.println("Numero de campos: ");
            m = sc.nextBigInteger();
            int y = 1;
            for (BigInteger i = BigInteger.valueOf(0); i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
                String cad = "";
                String cad1 = "";
                System.out.println("Tamaño de la llave para el registro " + (i.add(new BigInteger("1"))));
                int k = sc.nextInt();
                cad = RandomNum(k, 0, "");
                Registro ptr = null;
                for (BigInteger j = BigInteger.valueOf(0); j.compareTo(m) < 0; j = j.add(BigInteger.ONE)) {
                    System.out.println("Tamaño del campo " + (j.add(BigInteger.ONE)));
                    int t = sc.nextInt();
                    System.out.println("Tipo de dato: 0 = numero, 1 = letras");
                    int sw = sc.nextInt();
                    if (sw == 0) {
                        cad1 = RandomNum(t, 0, "");
                    } else {
                        cad1 = RandomLetter(abc, t, 0, "");
                    }
                    Registro p = new Registro(cad1);
                    if (ptr == null) {
                        ptr = p;
                    } else {
                        Registro q = ptr;
                        while (q.link != null) {
                            q = q.link;
                        }
                        q.link = p;
                    }
                }
                hash.put(cad, ptr);
            }
            Enumeration<Registro> enumeration = hash.elements();
            Enumeration<String> enumerationkeys = hash.keys();
            while (enumeration.hasMoreElements()) {
                Registro q = enumeration.nextElement();
                System.out.print(enumerationkeys.nextElement());
                while (q != null) {
                    System.out.print(" - " + q.info);
                    q = q.link;
                }
                System.out.println("");
            }
        }
        while (mn == 0) {
            mn = 0;
            mn1 = 0;
            mn2 = 0;
            mn3 = 0;
            mn4 = 0;
            mn5 = 0;
            System.out.println("----------------------------------------");
            System.out.println("1)- Ordenas los registros por su clave");
            System.out.println("2)- Buscar");
            System.out.println("3)- Mayor");
            System.out.println("4)- Menor");
            System.out.println("5)- Promedio");
            System.out.println("6)- Moda");
            System.out.println("7)- Numero de veces que esta una cadena");
            System.out.println("8)- salir");
            int op = sc.nextInt();
            if (op == 1) {
                Iterator it = hash.keySet().iterator();
                Vector v = new Vector(hash.keySet());
                Collections.sort(v);
                it = v.iterator();
                while (it.hasNext()) {
                    String element = (String) it.next();
                    Registro q = hash.get(element);
                    System.out.print(element);
                    while (q != null) {
                        System.out.print(" - " + q.info);
                        q = q.link;
                    }
                    System.out.println("");
                }
                System.out.println("");
            } else {
                if (op == 2) {
                    while (mn1 == 0) {
                        System.out.println("Opciones:");
                        System.out.println("0) - salir");
                        System.out.println("1) - Por clave");
                        for (BigInteger i = BigInteger.valueOf(1); i.compareTo(m) <= 0; i = i.add(BigInteger.ONE)) {
                            System.out.println((i.add(BigInteger.ONE)) + (")") + " - " + "Por " + "campo numero " + i);
                        }
                        int op2 = sc.nextInt();
                        if (op2 == 1) {
                            System.out.println("Digite la clave del registro a buscar");
                            String cb = sc.next();
                            if (hash.containsKey(cb)) {
                                Registro q = hash.get(cb);
                                System.out.print("El registro buscado es: ");
                                System.out.print(cb);
                                while (q != null) {
                                    System.out.print(" - " + q.info);
                                    q = q.link;
                                }
                            } else {
                                System.out.println("El elemento con esta clave no existe");
                            }
                        } else {
                            if (op2 == 0) {
                                mn1 = 1;
                            } else {
                                System.out.println("Digite la clave del registro a buscar");
                                String cb = sc.next();
                                buscarporcampo(hash, cb, op2);
                            }
                        }
                        System.out.println("");
                    }
                } else {
                    if (op == 3) {
                        while (mn2 == 0) {
                            System.out.println("Opciones:");
                            System.out.println("0) - salir");
                            System.out.println("1) - para la clave");
                            for (BigInteger i = BigInteger.valueOf(1); i.compareTo(m) <= 0; i = i.add(BigInteger.ONE)) {
                                System.out.println((i.add(BigInteger.ONE)) + (")") + " - " + "Para el " + "campo numero " + i);
                            }
                            int op3 = sc.nextInt();
                            if (op3 == 1) {
                                mayor_por_key(hash);
                            } else {
                                if (op3 == 0) {
                                    mn2 = 1;
                                } else {
                                    encontrar_mayor(hash, op3);
                                }
                            }
                            System.out.println("");
                        }
                    } else {
                        if (op == 4) {
                            while (mn3 == 0) {
                                System.out.println("Opciones:");
                                System.out.println("0) - salir");
                                System.out.println("1) - para la clave");
                                for (BigInteger i = BigInteger.valueOf(1); i.compareTo(m) <= 0; i = i.add(BigInteger.ONE)) {
                                    System.out.println((i.add(BigInteger.ONE)) + (")") + " - " + "Para el " + "campo numero " + i);
                                }
                                int op4 = sc.nextInt();
                                if (op4 == 1) {
                                    menor_por_key(hash);
                                } else {
                                    if (op4 == 0) {
                                        mn3 = 1;
                                    } else {
                                        encontrar_menor(hash, op4);
                                    }
                                }
                                System.out.println("");
                            }
                        } else {
                            if (op == 5) {
                                while (mn4 == 0) {
                                    System.out.println("Opciones:");
                                    System.out.println("0) - salir");
                                    System.out.println("1) - para la clave");
                                    for (BigInteger i = BigInteger.valueOf(1); i.compareTo(m) <= 0; i = i.add(BigInteger.ONE)) {
                                        System.out.println((i.add(BigInteger.ONE)) + (")") + " - " + "Para el " + "campo numero " + i);
                                    }
                                    int op5 = sc.nextInt();
                                    if (op5 == 0) {
                                        mn4 = 1;
                                    } else {
                                        if (op5 == 1) {
                                            hallar_promedio_key(hash, n);
                                        } else {
                                            hallar_promedio(hash, op5, n);
                                        }
                                    }
                                    System.out.println("");
                                }
                            } else {
                                if (op == 6) {
                                    while (mn5 == 0) {
                                        System.out.println("Opciones:");
                                        System.out.println("0) - salir");
                                        for (BigInteger i = BigInteger.valueOf(1); i.compareTo(m) <= 0; i = i.add(BigInteger.ONE)) {
                                            System.out.println((i) + (")") + " - " + "Para el " + "campo numero " + i);
                                        }
                                        int op6 = sc.nextInt();
                                        if (op6 == 0) {
                                            mn5 = 1;
                                        } else {
                                            hallar_moda(hash, op6);
                                        }
                                        System.out.println("");
                                    }
                                } else {
                                    if (op == 7) {
                                        String cab;
                                        System.out.println("Digite la cadena a buscar");
                                        cab = sc.next();
                                        nvc(hash,cab);
                                    } else {
                                        if (op == 8) {
                                            mn = 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

    }

    public static String RandomNum(int tam, int i, String numbers) {
        if (i < tam) {
            String num = "" + ((int) (Math.random() * 10));
            numbers += num;
            return RandomNum(tam, i + 1, numbers);
        }
        return numbers;

    }

    public static String RandomLetter(String[] letras, int tam, int i, String letters) {
        if (i < tam) {
            int num = (int) (Math.random() * 26);
            letters += letras[num];
            return RandomLetter(letras, tam, i + 1, letters);
        }
        return letters;
    }

    private static void buscarporcampo(Hashtable<String, Registro> table, String nb, int op) {
        int sw = 0;
        String key, res;
        Enumeration<Registro> enumeration = table.elements();
        Enumeration<String> enumerationkeys = table.keys();
        while (enumeration.hasMoreElements() && sw == 0) {
            Registro q = enumeration.nextElement();
            key = enumerationkeys.nextElement();
            res = "";
            for (int i = 1; i < op - 1; i++) {
                res = res + q.info + " - ";
                q = q.link;
            }
            if (q.info.equals(nb)) {
                sw = 1;
                System.out.print("El registro buscado es: ");
                System.out.print(key + " - " + res);
                while (q != null) {
                    System.out.print(q.info);
                    q = q.link;
                    if (q != null) {
                        System.out.print(" - ");
                    }
                }
            }
        }
        if (sw == 0) {
            System.out.println("El registro no existe");
        }
    }

    private static void encontrar_mayor(Hashtable<String, Registro> hash, int op3) {
        Enumeration<Registro> enumeration = hash.elements();
        int j = 1;
        String cadena1 = null;
        while (enumeration.hasMoreElements()) {
            Registro q = enumeration.nextElement();
            for (int i = 1; i < op3 - 1; i++) {
                q = q.link;
            }
            if (j == 1) {
                cadena1 = q.info;
                j = j + 1;
            } else {
                if (q.info.compareTo(cadena1) > 1) {
                    cadena1 = q.info;
                }
            }
        }
        System.out.println("El valor maximo del conjunto de registros correspondiente a este campo es: " + cadena1);
    }

    private static void encontrar_menor(Hashtable<String, Registro> hash, int op3) {
        Enumeration<Registro> enumeration = hash.elements();
        int j = 1;
        String cadena1 = null;
        while (enumeration.hasMoreElements()) {
            Registro q = enumeration.nextElement();
            for (int i = 1; i < op3 - 1; i++) {
                q = q.link;
            }
            if (j == 1) {
                cadena1 = q.info;
                j = j + 1;
            } else {
                if (q.info.compareTo(cadena1) < 1) {
                    cadena1 = q.info;
                }
            }
        }
        System.out.println("El valor minimo del conjunto de registros correspondiente a este campo es: " + cadena1);
    }

    private static void hallar_promedio(Hashtable<String, Registro> hash, int op4, BigInteger m) {
        Enumeration<Registro> enumeration = hash.elements();
        BigInteger prom = new BigInteger("0");
        String g;
        while (enumeration.hasMoreElements()) {
            Registro q = enumeration.nextElement();
            for (int i = 1; i < op4 - 1; i++) {
                q = q.link;
            }
            g = q.info;
            BigInteger s = new BigInteger(g);
            prom = prom.add(s);
        }
        prom = prom.divide(m);
        System.out.println("El valor promedio del conjunto de registros correspondiente a este campo es: " + prom);
    }
    
    private static void nvc(Hashtable<String, Registro> hash, String cab) {
        int c = 0;
        Enumeration<Registro> enumeration = hash.elements();
        while (enumeration.hasMoreElements()) {
            Registro q = enumeration.nextElement();
            for (BigInteger i = BigInteger.valueOf(1); i.compareTo(m) <= 0; i = i.add(BigInteger.ONE)) {
                if(q.info.contains(cab)){
                    c = c + 1;
                }
                q = q.link;
            }
        }
        System.out.println("La cadena se encuentra "+c+" "+"veces en el archivo");
    }

    private static void hallar_moda(Hashtable<String, Registro> hash, int op5) {
        Enumeration<Registro> enumeration = hash.elements();
        int cont2 = -1;
        String cad = null;
        while (enumeration.hasMoreElements()) {
            Registro q = enumeration.nextElement();
            for (int i = 1; i < op5; i++) {
                q = q.link;
            }
            int cont1 = -1;
            Enumeration<Registro> enumeration2 = hash.elements();
            while (enumeration2.hasMoreElements()) {
                Registro p = enumeration2.nextElement();
                for (int i = 1; i < op5; i++) {
                    p = p.link;
                }
                if (q.info.equals(p.info)) {
                    cont1 = cont1 + 1;
                    if (cont1 >= cont2) {
                        cad = q.info;
                        cont2 = cont1;
                    }
                }
            }
        }
        if (cont2 == 0) {
            System.out.println("Ningun valor se repite");
        } else {
            System.out.println("la moda es: " + cad);
        }
    }

    private static void mayor_por_key(Hashtable<String, Registro> hash) {
        Enumeration<String> enumerationkeys = hash.keys();
        String mayor = null;
        String g;
        int i = 1;
        while (enumerationkeys.hasMoreElements()) {
            g = enumerationkeys.nextElement();
            if (i == 1) {
                mayor = g;
                i = i + 1;
            } else {
                if (g.compareTo(mayor) > 0) {
                    mayor = g;
                }
            }
        }
        System.out.println("El valor maximo del conjunto de registros correspondiente a este campo es: " + mayor);
    }

    private static void menor_por_key(Hashtable<String, Registro> hash) {
        Enumeration<String> enumerationkeys = hash.keys();
        String menor = null;
        String g;
        int i = 1;
        while (enumerationkeys.hasMoreElements()) {
            g = enumerationkeys.nextElement();
            if (i == 1) {
                menor = g;
                i = i + 1;
            } else {
                if (g.compareTo(menor) < 0) {
                    menor = g;
                }
            }
        }
        System.out.println("El valor maximo del conjunto de registros correspondiente a este campo es: " + menor);
    }

    private static void hallar_promedio_key(Hashtable<String, Registro> hash, BigInteger m) {
        Enumeration<String> enumerationkeys = hash.keys();
        BigInteger prom = new BigInteger("0");
        while (enumerationkeys.hasMoreElements()) {
            BigInteger s = new BigInteger(enumerationkeys.nextElement());
            prom = (prom.add(s));
        }
        prom = prom.divide(m);
        System.out.println("El valor promedio del conjunto de registros correspondiente a este campo es: " + prom);
    }

    public static void cargar_txt(File archivo, Hashtable<String, Registro> hash) {
        int tam;
        try {
            Scanner scan = new Scanner(archivo);
            while (scan.hasNext()) {
                String linea = scan.nextLine();
                String[] vString = linea.split(";");
                tam = vString.length;
                String key = vString[0];
                Registro ptr = null;
                for (int i = 1; i < tam; i++) {
                    Registro p = new Registro(vString[i]);
                    if (ptr == null) {
                        ptr = p;
                        m = BigInteger.ONE;
                    } else {
                        Registro q = ptr;
                        while (q.link != null) {
                            q = q.link;
                        }
                        q.link = p;
                        m = m.add(BigInteger.ONE);
                    }
                }
                hash.put(key, ptr);
                n = n.add(BigInteger.ONE);
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            System.out.println("error");
        }
        Enumeration<Registro> enumeration = hash.elements();
        Enumeration<String> enumerationkeys = hash.keys();
        while (enumeration.hasMoreElements()) {
            Registro q = enumeration.nextElement();
            System.out.print(enumerationkeys.nextElement());
            while (q != null) {
                System.out.print(" - " + q.info);
                q = q.link;
            }
            System.out.println("");
        }
    }
}
