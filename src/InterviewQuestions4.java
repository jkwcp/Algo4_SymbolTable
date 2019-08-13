import edu.princeton.cs.algs4.StdOut;

public class InterviewQuestions4 {

    /**
     * Java autoboxing and equals(). Consider two 𝚍𝚘𝚞𝚋𝚕𝚎 values 𝚊 and 𝚋 and their corresponding 𝙳𝚘𝚞𝚋𝚕𝚎 values 𝚡 and 𝚢.
     * Find values such that (𝚊==𝚋) is 𝚝𝚛𝚞𝚎 but 𝚡.𝚎𝚚𝚞𝚊𝚕𝚜(𝚢) is 𝚏𝚊𝚕𝚜𝚎.
     * Find values such that (𝚊==𝚋) is 𝚏𝚊𝚕𝚜𝚎 but 𝚡.𝚎𝚚𝚞𝚊𝚕𝚜(𝚢) is 𝚝𝚛𝚞𝚎.
     */
    public static void main(String[] args) {
        //
        int x = 4;
        Double y = new Double(0.4);
        Double z = (double) x;

        StdOut.println(z == x);
        StdOut.println(z.equals(x));

    }
}
