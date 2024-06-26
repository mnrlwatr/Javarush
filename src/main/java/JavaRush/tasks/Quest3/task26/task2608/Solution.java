package JavaRush.tasks.Quest3.task26.task2608;

/* 
Мудрый человек думает раз, прежде чем два раза сказать
*/
public class Solution {
    private int var1;
    private int var2;
    private int var3;
    private int var4;
    private final Object lock1_2 = new Object();
    private final Object lock3_4 = new Object();

    public Solution(int var1, int var2, int var3, int var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public int getSumOfVar1AndVar2() {
        synchronized (lock1_2){
            return var1 + var2;
        }
    }

    public int getSumOfVar3AndVar4() {
        synchronized (lock3_4){
            return var3 + var4;
        }
    }

    public static void main(String[] args) {

    }
}
