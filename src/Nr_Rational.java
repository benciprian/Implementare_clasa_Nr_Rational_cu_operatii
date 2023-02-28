public class Nr_Rational {
      //declarare variabile membre ale clasei Nr_Rational
    private long numarator;             //declarare variabila tip long pentru numaratorul fractiei
    private long numitor;			    //declarare variabila tip long pentru numitorul fractiei
    //ex. 0.(3) = > 1/3 //ex.  1.57 => (157,100) => afisare 157/100

      //constructorii clasei Nr_rational
    public Nr_Rational()                              //constructor implicit pentru  clasa Nr_Rational
    {
        numarator=0;                                 //(numarator,numitor) = > (0,1)
        numitor=1;
    }
    public Nr_Rational(long numarator, long numitor) //constructor de  initializare pentru clasa Nr_Rational
    {
        this.numarator=numarator;          //"this" este referinta la obiectul implicit "numarator"
        if(numitor==0)                    //validarea numitorului - numitor, numitor!=0
            numitor=1;
        this.numitor=numitor;             //"this" este referinta la obiectul implicit "numitor"
        simplificare_fractie();
    }
    public Nr_Rational(long nr)                      //constructor dintr-un intreg
    {
    //    this(nr,1);                         // n => (n,1)
    }
    public Nr_Rational(double d)                     // constructor care transforma un intreg in numar rational
    {	                                            //construtor dintr-un numar double
        numitor=1;
        double pDouble=d;
        while (Math.floor(pDouble) != pDouble ){   //trebuie ca de ex.  14.872 = (14872,1000) ;  0.87 => (87,100)
            numitor*=10;
            pDouble=d*numitor;
        }

        this.numarator=(long) pDouble;		          //sau p=Math.floor(pDouble)
        simplificare_fractie();			          //se poate si this.simplifica()
    }

    //Accesori ai clasei Nr_Rational
    public long getNumarator()                       //accesează datele stocate în variabila membru "numărător"
    {
        return numarator;                          //returneaza valoarea numaratorului fractiei
    }

    public void setNumarator(long numarator)         //metoda care furnizeaza date pentru a stoca în variabila membru "numărător"
    {
        this.numarator = numarator;			         //"this" este referinta la obiectul implicit "numarator"
        simplificare_fractie();			             //la modificarea numaratorului simplificam fractia
    }
    //100/400 => 1/4;  -100/400 => -1/4
    public long getNumitor()                         //accesează datele stocate în variabila membru "numitor"
    {
        return numitor;                            //returneaza valoarea numitorului fractiei
    }
    public void setNumitor(long numitor)             //metoda care furnizeaza date pentru a stoca în variabila membru "numitor"
    {
        if(numitor==0)                            //daca numitor fractie = 0 se va  modifica in 1
            numitor=1;
        this.numitor = numitor;                   //"this" este referinta la obiectul implicit "numitor"
        simplificare_fractie();                   //la modificarea numitorului fractiei simplificam fractia
    }

    //Metode specifice ale clasei Nr_Rational

    private void valoareabsoluta()                   //metoda care calculeaza modulul(valoarea absoluta) dintr-o fracție ordinară
    {
        if(numarator*numitor>0) {
            numarator=Math.abs(numarator);	      // abs este metoda clasa(statica) din clasa Math
            numitor=Math.abs(numitor);			  //ca de ex. -6/7   => -6/7; 6/-7 => -6/7
        }							              //ca de ex. -10/-11  =>  10/11
        else{
            numarator=-Math.abs(numarator);
            numitor=Math.abs(numitor);			       //"numitor" este intotdeauna >0
        }

    }
    public void simplificare_fractie(){      //metoda care simplifica o fractie pana se obtine o fractie ireductibilă
       long cmd=cmmdc(Math.abs(numarator),numitor); //se apeleaza metoda cmmdc
       numarator=numarator/cmd;             //se simplifica numaratorul fractiei prin cmd (cel mai mare divizor comun)
       numitor=numitor/cmd;                 //se simplifica numitorul fractiei prin cmd(cel mai mare divizor comun)
   }

    private long cmmdc(long a, long b) //metoda care calculeaza cel mai mare divizor comun dintre numerele "a" si "b"
    {
        return b == 0 ? a : cmmdc(b, a % b); // apel recursiv al metodei cmmdc
    }      //operator ternar - daca "b = 0"(adevarata)  atunci se executa "a",altfel daca b!=0 (la apel) atunci se apeleaza metoda
           // de calcul a cmmdc a doua numere


    // obiect.camp_Instanta
    public void aduna(Nr_Rational b)
    {			                                                      // adica a <-  a+b  apel:  a.aduna(b)
        numarator=numarator*b.numitor + numitor*b.numarator;		 //inmultirea clasica a 2 fractii
        numitor=numitor*b.numitor;					                // this <- this+b
        simplificare_fractie();
    }

    public void aduna(Nr_Rational a, Nr_Rational b)  //metoda care aduna doua numere rationale "a" si "b"
    {   //in acest caz se aduna doua fractii cu numitorii diferiti(trebuie amplificate)
        numarator=a.numarator*b.numitor + a.numitor*b.numarator;
        numitor=a.numitor*b.numitor;
        simplificare_fractie(); //this <- a+b  apel:    c.aduna(a,b)
    }
    public boolean egalZero()                        //metoda care verifica daca numaratorul fractiei este egal cu zero
    {
        if(numarator==0)
            return true;        //daca numarator = 0 atunci returneaza adeverat daca nu returneaza fals
        return false;
    }
    public boolean numitorUnu()                      //metoda care verifica daca numitorul fractiei este egal cu unu
    {
        if(numitor==1)
            return true; //daca numitor = 1 atunci returneaza adevarat daca nu returneaza fals
        return false;
    }
    public boolean maiMicEgal(Nr_Rational a)
    {                                                        //daca this>=a return true,
        if(numarator*a.numitor<=numitor*a.numarator)
            return true;		                             //apel x.maiMareEgal(y)
          return false;                                     //?merge indiferent de semne
    }
}
