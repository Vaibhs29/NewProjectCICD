public class Non_repeating_second_char {
    public static void main(String[] args) {


        //Find the First Non-Repeating Character
        //Given a string, find the first non-repeating character.

        String input = "Vaibhav";
        String str = input.toLowerCase();
        boolean isRepeatable;
        int count=0;

        for (int i = 0; i < str.length(); i++) {
            isRepeatable = false;


            for (int j = i + 1; j < str.length(); j++) {


                if (str.charAt(i) == str.charAt(j)) {

                    isRepeatable = true;
                    break;

                }



            }

            count++;



            if (!isRepeatable && count==1) {

                System.out.println("first non-repeating character in string is: " + str.charAt(i));
                return;
            }
        }

        System.out.println("Non-Repeating character in string");
    }
}
