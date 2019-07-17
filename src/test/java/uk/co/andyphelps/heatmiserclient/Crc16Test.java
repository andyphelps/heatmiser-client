package uk.co.andyphelps.heatmiserclient;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Crc16Test {

    private Crc16 crc16 = new Crc16();

    @Test
    public void whenCrcCheckByteArrayThenCrcIsCorrect() {
        final Byte[] data = IntStream.of(0x93, 0x0b, 0x00, 0x12, 0x34, 0x00, 0x00, 0xff, 0xff)
                .boxed()
                .map(Integer::byteValue)
                .toArray(Byte[]::new);

        final int crc = crc16.calculateCrc16(data);

        assertThat(crc).isEqualTo(0x06f6);

    }
}
