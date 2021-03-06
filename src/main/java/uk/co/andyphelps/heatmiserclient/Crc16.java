package uk.co.andyphelps.heatmiserclient;

class Crc16 {

    private static final int[] LOOKUP_HIGH = new int[]{
            0x00, 0x10, 0x20, 0x30, 0x40, 0x50, 0x60, 0x70,
            0x81, 0x91, 0xA1, 0xB1, 0xC1, 0xD1, 0xE1, 0xF1};

    private static final int[] LOOKUP_LOW = new int[]{
            0x00, 0x21, 0x42, 0x63, 0x84, 0xA5, 0xC6, 0xE7,
            0x08, 0x29, 0x4A, 0x6B, 0x8C, 0xAD, 0xCE, 0xEF};

    private int high;
    private int low;

    int calculateCrc16(final Byte[] data) {
        high = 0xff;
        low = 0xff;

        for (Byte datum: data) {
            update(chop(datum));
        }

        return (high << 8) + low;
    }

    private void update(final int datum) {
        updateNibble(chop(datum >> 4));
        updateNibble(chop(datum & 0x0F));
    }

    private void updateNibble(int nibble) {
        int t = chop(high >> 4);

        t = chop(t ^ nibble);

        high = chop(high << 4) | chop(low >> 4 );
        low = chop(low << 4);

        high = chop(high ^ LOOKUP_HIGH[t]);
        low = chop(low ^ LOOKUP_LOW[t]);
    }

    private int chop(int b) {
        return b & 0xFF;
    }
}