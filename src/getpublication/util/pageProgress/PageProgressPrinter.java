package getpublication.util.pageProgress;

public class PageProgressPrinter {

    private static final int TOTAL_CELLS = 30;
    private int totalPages = 0;
    private int currentPage = 0;
    private String messageBefore = "";
    private PrintStrategy printStrategy = null;

    private static PageProgressPrinter instance = null;

    private PageProgressPrinter() {
    }

    public static PageProgressPrinter getInstance() {
        if (instance == null) {
            instance = new PageProgressPrinter();
        }

        return instance;
    }

    public synchronized void setPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
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

        this.printStrategy.execute(this.currentPage, this.totalPages,
                TOTAL_CELLS, this.messageBefore);
    }

    public synchronized void newLine() {
        System.out.println();
    }
}
