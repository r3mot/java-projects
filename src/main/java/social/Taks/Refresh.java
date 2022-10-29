package social.Taks;

import javafx.concurrent.Task;

public class Refresh extends Task<Long>{

    private final long limit;

    public Refresh(long limit) {
        this.limit = limit;
    }

    @Override
    protected Long call() throws Exception {
        
        long sum = 0;
        for(int i = 0; i < limit; i++){
            sum += i;
        }
        return sum;
    }
    
}
