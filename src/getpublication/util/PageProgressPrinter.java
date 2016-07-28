package getpublication.util;

public class PageProgressPrinter {

    private int totalPages = 0;
    private int currentPage = 0;
    private String messageBefore = "";

    private static PageProgressPrinter instance = null;

    private PageProgressPrinter() {
    }

    public static PageProgressPrinter getInstance() {
        if (instance == null) {
            instance = new PageProgressPrinter();
        }

        return instance;
    }
    
    public synchronized void resetCounters() {
        this.currentPage = 0;
    }

    public synchronized void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public synchronized void setMessageBefore(String message) {
        this.messageBefore = message;
    }

    public synchronized void printProgress(int newPages) {
        if (newPages < 0) {
            newPages = 0;
        }

        if (this.currentPage + newPages <= totalPages) {
            this.currentPage += newPages;
        } else {
            this.currentPage = this.totalPages;
        }

        System.out.print("\r " + this.messageBefore + " [");
        for (int index = 0; index < this.currentPage; ++index) {
            System.out.print("=");
        }
        for (int index = 0; index < (this.totalPages
                - this.currentPage); ++index) {
            System.out.print("_");
        }
        System.out.print("] pages = " + this.totalPages);
    }

    public synchronized void newLine() {
        System.out.println();
    }
}
