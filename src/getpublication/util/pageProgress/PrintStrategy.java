package getpublication.util.pageProgress;

public abstract class PrintStrategy {

    public abstract void execute(int currentPages, int totalPages, int length, String message);
}
