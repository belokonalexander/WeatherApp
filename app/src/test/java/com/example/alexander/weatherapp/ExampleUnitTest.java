package com.example.alexander.weatherapp;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */




public class ExampleUnitTest {



    @Test
    public void rx_test() throws InterruptedException {

        TestObserver<Integer> testObserver = TestObserver.create();
        final CountDownLatch signal = new CountDownLatch(1);

        SingleSource<Integer> s1 = Single.fromCallable(() -> {
            Thread.sleep(1500);
            return 1;
        });
        SingleSource<Integer> s2 = Single.fromCallable(() -> {
            if(1==1)
                throw new RuntimeException();
            signal.countDown();
            return 6;
        });

        Single.concat(s1,s2).toObservable().subscribeOn(Schedulers.newThread())
            .subscribe(testObserver);

        signal.await(5, TimeUnit.SECONDS);

        testObserver.assertValueAt(0, integer -> integer==1);
    }

/*
    @Test
    public void hackerrank(){

        String str = "+-++++++++\n" +
                "+-------++\n" +
                "+-++-+++++\n" +
                "+-------++\n" +
                "+-++-++++-\n" +
                "+-++-++++-\n" +
                "+-++------\n" +
                "+++++++++-\n" +
                "++++++++++\n" +
                "++++++++++\n" +
                "ANDAMAN;MANIPUR;ICELAND;ALLEPY;YANGON;PUNE";

        Scanner in = new Scanner(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)));
        int n = 10;
        char[][] array = new char[n][n];


        for(int i =0; i < n; i++) {
            String line = in.next();
            for (int j = 0; j < n; j++) {
                array[i][j] = line.charAt(j);
            }
        }

        List<String> words =  Arrays.asList(in.next().split("\\s*;\\s*"));




        //fill(array, words.get(0),0);
        Mode result = null;


        for(int i =0; i < n; i++){
            for(int j =0; j < n; j++){
                if(array[i][j]=='-'){
                    result = execute(Arrays.copyOf(array,array.length),i,j,words,n);
                }
            }
        }

        System.out.println(result.result);
        for(int i =0; i < n; i++){
            System.out.println();
            for(int j =0; j < n; j++){
                System.out.print(result.array[i][j]);
            }
        }

    }

    class Mode {
        char[][] array;
        boolean result;

        public Mode(char[][] array, boolean result) {
            this.array = array;
            this.result = result;
        }
    }

    public Mode execute(char[][] arr, int i, int j, List<String> words, int n){


        System.out.println(" I : " + i + " / J : " + j + " -> " + words);


        boolean right = false;

        if(j-1>=0 && arr[i][j-1]!='+' || j+1<n && arr[i][j+1]!='+')
            right = true;



        List<Integer> newDotsI = new ArrayList<>();
        List<Integer> newDotsJ = new ArrayList<>();


        //int pivotJ = j;
        //int pivotI = i;
        boolean stat = true;

        for(int y =0; y < n; y++){
            for(int yy =0; yy < n; yy++){
                if(arr[y][yy]=='-'){
                    stat = false;
                    break;
                }

            }
        }

        Mode resultat = new Mode(arr,stat);

        if(right){
            while(j>=0 && arr[i][j]!='+'){
                j--;
            }

            j=j+1;

            System.out.println("FIRST LETTER: " + arr[i][j]);

            int length = 0;
            int newJ = j;
            while(newJ < n && arr[i][newJ]!='+'){

                if(i+1<n && arr[i+1][newJ]=='-'){
                    newDotsI.add(i+1);
                    newDotsJ.add(newJ);
                }

                length++;
                newJ++;
            }

            //System.out.println(" L " + length);

            int index = -1;
            for(String word : words){
                index++;
                System.out.println(" all -----> " + word + " --> " + index);
                if(word.length()!=length)
                    continue;

                int pos = 0;
                boolean res = true;
                newJ = j;
                while(newJ < n && arr[i][newJ]!='+'){
                    if(arr[i][newJ]!='-' && word.charAt(pos)!=arr[i][newJ]){
                        res = false;
                        index++;
                        break;
                    }
                    pos++;
                    newJ++;
                }

                if(res){
                    //System.out.println(" LA L ALA ");
                    char[][] copyarr = new char[n][n];
                    for(int y =0; y < n; y++){

                        for(int yy =0; yy < n; yy++){
                            copyarr[y][yy] = arr[y][yy];
                        }
                    }
                    newJ = j;
                    int indexx = 0;
                    while(newJ < n &&  copyarr[i][newJ]!='+' ){
                        copyarr[i][newJ] = word.charAt(indexx);
                        indexx++;
                        newJ++;
                    }


                    List<String> copy = new ArrayList<>();
                    for(String s : words)
                        copy.add(s);

                    System.out.println(" Delete 1: " + copy.get(index));
                    copy.remove(index);

                    if(newDotsI.size()==0){
                        return new Mode(copyarr,true);
                    }

                    for(int k =0; k < newDotsI.size(); k++){
                        Mode it;
                        if((it = execute(copyarr,newDotsI.get(k),newDotsJ.get(k),copy,n)).result){

                            for(int y =0; y < n; y++){
                                System.out.println();
                                for(int yy =0; yy < n; yy++){
                                    System.out.print(arr[y][yy]);
                                }
                            }

                            resultat = it;
                        };
                    }

                }

            }

        } else {


            while(i>=0 && arr[i][j]!='+'){
                i--;
            }

            i = i+1;

            System.out.println("FIRST LETTER: " + arr[i][j]);

            int length = 0;
            int newI = i;



            while(newI < n && arr[newI][j]!='+'){

                if(j+1<n && arr[newI][j+1]=='-'){
                    newDotsI.add(newI);
                    newDotsJ.add(j+1);
                }

                length++;
                newI++;
            }



            int index = -1;
            for(String word : words){
                index++;
                //System.out.println(" L " + length + " -> " + word);
                System.out.println(" all -----> " + word + " --> " + index);
                if(word.length()!=length)
                    continue;

                //System.out.println(" LL " + length + " -> " + word);

                int pos = 0;
                boolean res = true;
                newI = i;
                while(newI < n && arr[newI][j]!='+'){
                    if(arr[newI][j]!='-' && word.charAt(pos)!=arr[newI][j]){
                        res = false;

                        break;
                    }
                    newI++;
                    pos++;
                }

                if(res){

                    char[][] copyarr = new char[n][n];
                    for(int y =0; y < n; y++){

                        for(int yy =0; yy < n; yy++){
                            copyarr[y][yy] = arr[y][yy];
                        }
                    }

                    newI = i;
                    int indexx = 0;
                    while(newI < n && copyarr[newI][j]!='+'){
                        copyarr[newI][j] = word.charAt(indexx);
                        indexx++;
                        newI++;
                    }

                    List<String> copy = new ArrayList<>();
                    for(String s : words)
                        copy.add(s);

                    System.out.println(" Delete 2: " + copy.get(index));
                    copy.remove(index);

                    if(newDotsI.size()==0){
                        return new Mode(copyarr,true);
                    }

                    for(int k =0; k < newDotsI.size(); k++){
                        Mode it;
                        //System.out.println(newDotsI.get(k) + " " + newDotsJ.get(k));
                        if((it = execute(copyarr,newDotsI.get(k),newDotsJ.get(k),copy,n)).result){

                            resultat = it;
                        };
                    }

                }

            }

        }

        stat = true;
        for(int y =0; y < n; y++){
            for(int yy =0; yy < n; yy++){
                if(arr[y][yy]=='-'){
                    stat = false;
                    break;
                }

            }
        }

        if(stat) {
            System.out.println("ANSWER");
            for(int y =0; y < n; y++){
                if(y!=0) System.out.println();
                for(int yy =0; yy < n; yy++){
                    System.out.print(arr[y][yy]);
                }
            }
        }


        return resultat;

    }

*/
}