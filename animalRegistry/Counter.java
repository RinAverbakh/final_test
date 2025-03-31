package animalRegistry;
class Counter implements AutoCloseable {
    private int count;
    private boolean isClosed = false;

    public Counter() {
        this.count = 0;
    }

    public void add() throws IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Счётчик выключен. Невозможно добавить");
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws Exception {
        if (isClosed) {
            throw new IllegalStateException("Счётчик уже выключен.");
        }
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

}
