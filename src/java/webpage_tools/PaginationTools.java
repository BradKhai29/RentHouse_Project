package webpage_tools;

public final class PaginationTools {
    private static int currentCnt = 1;
    private static int lastHouseID = 1;
    
    public static int generate() {
        return currentCnt++;
    }

    public static int getCurrentCnt() {
        return currentCnt;
    }

    public static void setCurrentCnt(int currentCnt) {
        PaginationTools.currentCnt = currentCnt;
    }

    public static int getLastHouseID() {
        return lastHouseID;
    }

    public static void setLastHouseID(int lastHouseID) {
        PaginationTools.lastHouseID = lastHouseID;
    }
    
    public static void resetCnt() {
        currentCnt = 1;
    }
}
