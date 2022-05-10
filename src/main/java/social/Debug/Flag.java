package social.Debug;

public class Flag {
    
    private static boolean debugging = false;

    public static void DEBUG(String error, Exception e){
        if(debugging){
            System.out.println(error);
            System.out.println("Caused by: " + e.getCause());
        }
    }
}
