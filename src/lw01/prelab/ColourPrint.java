public class ColourPrint extends PrintJob {
    public ColourPrint(String id, int pages) { super(id, pages); }

    // 10 halaman pertama @1.500, sisanya @1.000, ditambah setup 2.000 per copy
    @Override
    public int calculateCharge() {
        int p = getPages();
        return Math.min(p, 10) * 1500 + Math.max(p - 10, 0) * 1000 + 2000;
    }

    @Override
    public String label() { return "Colour"; }
}
