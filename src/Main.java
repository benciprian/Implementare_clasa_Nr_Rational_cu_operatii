import	java.io.*;
import java.util.Scanner;

public class Main {

    public static long citLong(String sir) {   //metoda care converteste un string intr-un long
        System.out.print(sir);
        try {
            Scanner s =new Scanner(System.in);
            long    l =s.nextLong();
            return  l;
        }
        catch(Exception exp) {
            System.out.println("Ai gresit!! Introduceti un  numar intreg");
            return citLong(sir);
        }
    }

    public static Nr_Rational[] citire_Tablou_Nr_Rationale() {
        InputStreamReader stdin = new InputStreamReader(System.in);
        BufferedReader console  = new BufferedReader(stdin);
        String linie;				                                  //un obiect linie unde se citesc numerele separate prin virgule

        try
        { System.out.print("Introduceti numerele separate cu (,):");
            linie= console.readLine();			                     //citire efectiva in linie a numerelor rationale

            String[] tablouSiruri = linie.split(","); 	        //separarea in vectorul tablouSiruri


            double real[]=new double[tablouSiruri.length];         //declarare tablou de numere reale si instantiere cu operatorul "new"

            for(int i=0;i<tablouSiruri.length;i++)
                real[i]=Double.parseDouble(tablouSiruri[i]);        //conversia elementelor din tablouSiruri(string)  in double(real)

            Nr_Rational[] r= new Nr_Rational[real.length];	        //instantiere obiecte rationale
            for (int i=0;i<r.length;i++) {
                r[i]=new Nr_Rational(real[i]);
            }

            return r;
        }
        catch(IOException ioex)
        { System.out.println("Eroare intrare!!");
            return null;

        }
    }
    public static Nr_Rational cel_mai_mic_nrRational(Nr_Rational r[]) { //metoda care returneaza cel mai mic nr.rational din sirul introdus
        Nr_Rational celMic=r[0];//declarare variabila "celMare" de tip Nr_rational pentru cel mai mare nr rational din sir,
                               // si initializarea acesteia cu primul element din tablou
        for (Nr_Rational rat : r) {  //bucla for pentru parcurgerea tuturor elementelor din tablou,
            if(rat.maiMicEgal(celMic)) {  //apelarea functiei "maiMicEgal" in cadrul instructiunii if
                celMic=rat;
            }
        }
        return celMic; //returnare cel mai mic numar rational din sirul introdus
    }
    public static Nr_Rational cel_mai_mare_nrRational(Nr_Rational r[]) { //metoda care returneaza cel mai mare nr.rational din sirul introdus
        Nr_Rational celMare=r[0]; //declarare variabila "celMare" de tip Nr_rational pentru cel mai mare nr rational din sir,
                                 // si initializarea acesteia cu primul element din tablou
        for (Nr_Rational rat : r) {   //bucla for pentru parcurgerea tuturor elementelor din tablou,
            if(!rat.maiMicEgal(celMare)) {  //apelarea functiei "maiMicEgal" in cadrul instructiunii if,
                celMare=rat;
            }
        }
        return celMare; //returnare cel mai mare numar rational din sirul introdus
    }
    public static void afisare_sir_Nr_Rationale(String sir, Nr_Rational rat) { //metoda care afiseaza sirul de numere rationale
        System.out.print(sir);
        if(rat.egalZero() || rat.numitorUnu()) // se face verificarea prin apelarea functiei "egalZero" daca numaratorul este "0" sau prin
                                              //apelarea functiei "numitorUnu" daca numitorul este "1" (ex "0" sau "0/1")
            System.out.print(rat.getNumarator()); //se afiseaza numaratorul fractiei
        else
            System.out.print(rat.getNumarator()+"/"+rat.getNumitor()+" "); //se afiseaza fractia (numarator/numitor)
    }
    public static int meniu() { //metoda pentru meniu de tip consola a aplicatiei
        System.out.println("\n\n1. Citire sir numere rationale");
        System.out.println("2. Afisare sir numere rationale");
        System.out.println("3. Afisare cel mai mic numar rational");
        System.out.println("4. Afisare cel mai mare numar rational");
        System.out.println("5. Afisare suma");
        System.out.println("0. Terminare program");
        return (int) citLong("alege optiune:");

    }

    public static void main(String[] args) { //functia principala main
        int optiune;           //declarare obiect "optiune" de tip intreg .
        Nr_Rational[] r=null; //declarare tablou de numere rationale (r) și inițializarea acestuia cu valoarea null.
        Nr_Rational celMic=new Nr_Rational();//declarare obiect "celMic" de tip nr_rational si instanțiat cu operatorul "new".
        Nr_Rational celMare=new Nr_Rational();//declarare obiect "celMare" de tip nr_rational si instanțiat cu operatorul "new".

        optiune=meniu();

        while(optiune!=0) {
            switch(optiune) {
                case 1:
                    r=citire_Tablou_Nr_Rationale();	  //apelare functie citire  de la tastatura "tablou nr rationale" cu split
                    break;
                case 2:
                    System.out.println("Sirul de numere rationale introdus de la tastatura este:");
                    for(Nr_Rational rat:r)
                    {
                        afisare_sir_Nr_Rationale(" ",rat);  //apelare funcție  "afișare șir nr numere raționale"
                    }
                    break;
                case 3:
                    celMic=cel_mai_mic_nrRational(r); //apelare functie care gaseste cel mai mic nr rational din sirul introdus
                    afisare_sir_Nr_Rationale("Cel mai mic numar rational din sirul introdus este =", celMic);//apelare funcție  "afișare șir nr numere raționale"
                    break;                                                                                       //pentru afisarea "celui mai mic nr rational" din sir
                case 4:
                    celMare=cel_mai_mare_nrRational(r); //apelare functie care gaseste cel mai mare nr rational rational din sirul introdus
                    afisare_sir_Nr_Rationale("Cel mai mare numar rational din sirul este =", celMare); // apelare funcție  "afișare șir nr numere raționale"
                    break;                                                                                //pentru afisarea "celui mai mare nr rational" din sir
                case 5:
                    Nr_Rational suma = new Nr_Rational(); //declarare obiect "suma" de tip nr rational si instantierea acestuia cu "new"
                    suma.aduna(celMic,celMare);          //apelarea metodei care calculeaza suma dintre doua numere rationale
                    afisare_sir_Nr_Rationale("Cel mic + cel mare = ", suma); //apelarea metodei "afisare_sir_Nr_Rationale" pentru afisarea sumei
                    break;                                                      //dintre "cel mai mic nr rational" și "cel mai mare nr rational"
                default:
                    System.out.println("Ai gresit optiunea introdusă!!"); //se afiseaza acest mesaj in cazul in care utilizatorul alege o alta optiune
            }                                                             //in afara de cele propuse in cadrul functiei meniu.
            optiune=meniu();
        }
        System.out.println("Program finalizat !!!"); //in cazul in care utilizatorul introduce optiunea zero consola va afisa "Program finalizat!"
    }
}



