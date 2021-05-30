package com.multithreading;

class Printer
{
    //synchronized public void  PrintDocument(int noofcopies, String docname)
    public void PrintDocument(int noofcopies, String docname)
    {
        for (int i = 0; i < noofcopies; i++) {
           System.out.println("printing doc>> "+docname + " "+ i );
        }
    }
}

class Mythread extends Thread
{
    Printer pref;

    Mythread(Printer ref)
    {
        pref = ref;
    }

    @Override
    public void run()
    {
        synchronized (pref) {
            pref.PrintDocument(5, "john");
        }
    }
}

class YourThread extends Thread
{
    Printer pref;

    YourThread(Printer pref)
    {
        this.pref = pref;
    }

    @Override
    public void run()
    {
        synchronized(pref) {   //synchronized block
            pref.PrintDocument(5, "tommy");
        }
    }
}

class ThirdThread implements Runnable
{
    Printer pref;

    ThirdThread(Printer pref)
    {
        this.pref = pref;
    }

    @Override
    public void run() {
        pref.PrintDocument(5," Zookeeper");
    }
}

public class MultithreadCheck {

    public static void main(String args[]) throws InterruptedException {
        Printer p = new Printer();

        Mythread t1 = new Mythread(p);
        YourThread t2 = new YourThread(p);
        System.out.println("join example");
        t1.start();
        t1.join();    // single printer object shared between two threads , join makes to executed one thread first then next
        t2.start();
        t2.join();
//------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println();
        System.out.println("Synchronized example");
        Mythread t3 = new Mythread(p);
        YourThread t4 = new YourThread(p);
        t3.start();
        t4.start();

        //---------------------------------------------------------------------------

        Thread last = new Thread(new ThirdThread(p));
        last.start();
    }
}
