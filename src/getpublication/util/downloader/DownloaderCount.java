package getpublication.util.downloader;

public class DownloaderCount {
    
    private int threadCount = 0;
    private boolean blocked = false;
    
    public void increment() {
        ++this.threadCount;
    }
    
    public void decrement() {
        --this.threadCount;
    }
    
    public int getThreadCount() {
        return this.threadCount;
    }
    
    public void block() {
        this.blocked = true;
    }
    
    public void unblock() {
        this.blocked = false;
    }
    
    public boolean isBlocked() {
        return this.blocked;
    }
}
