package social.Debug;

public class Flag {
    
    private static boolean debugging = true;

    public static void DEBUG(String error){
        if(debugging){
            System.out.println("Caused by: " + error);
        }
    }

    public static void PROCESS(String message){
        if(debugging){
            System.out.println(message);
        }
    }
}
