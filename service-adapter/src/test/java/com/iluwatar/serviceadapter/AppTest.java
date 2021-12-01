package com.iluwatar.serviceadapter;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {
    @Test
    void shouldExecuteWithoutException() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
