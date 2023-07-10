package org.gomez.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMoneda {
    EURO(0.8 ),
    YEN(null);
        private final Double valor;
}
