package exam1;

import java.util.Scanner;

public class exam1 {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();//rows
        int m=sc.nextInt();//column;
        String[] board=new String[n];
        for (int i=0;i<n;i++) {

                board[i] = sc.next();

//                System.out.println(board[i][j]);

        }
//        }
        for (int i=0;i<n;i++){
//            for(int j=0;j<m;j++) {
                System.out.print(board[i]);
//            }
//            System.out.print("/n");
            }
        int k=sc.nextInt();
        int sum=0;
//        for (int i=0;i<n;i++) {
//            for (int j = 0; j < m; j++) {
//                if(board[i][j]=="o"){
//                    sum++;
//                }
//            }
//            }


        int steps=0;
//        while(true){
//           up(board);
//           steps++;
//           if(check(board,n,m)==k){
//               break;
//           }
//
//        }
//        while(true){
//            down(board);
//            steps++;
//            if(check(board,n,m)==k){
//                break;
//            }
//
//        }
//        while(true){
//            left(board);
//            steps++;
//            if(check(board,n,m)==k){
//                break;
//            }
//        }
//        while(true){
//            right(board);
//            steps++;
//            if(check(board,n,m)==k){
//                break;
//            }
//        }
        System.out.print(sum);

    }
   static   void up(String [][]boards){
        for(int i=0;i<boards[0].length;i++){
            boards[0][i]=".";

        }
    }

   static void down(String [][]boards){
        for(int i=0;i<boards[0].length;i++){
            boards[boards.length-1][i]=".";
        }
    }
   static void left(String [][]boards) {
        for (int i = 0; i < boards.length; i++) {
            boards[i][0] = ".";

        }
    }
   static void right(String [][]boards) {
        for (int i = 0; i < boards.length; i++) {
            boards[boards.length-1][0] = ".";

        }
    }
    static int check(String [][]boards,int n,int m){
       int num=0;
       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               if("o"==boards[i][j]){
                   num++;
               }
           }
       }
       return num;
    }
}
