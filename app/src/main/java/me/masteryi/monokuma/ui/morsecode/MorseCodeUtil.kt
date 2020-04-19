package me.masteryi.monokuma.ui.morsecode

import java.util.*
import kotlin.collections.HashMap

/**
 * @author Ethan Lee
 */
class MorseCodeUtil {
    companion object {
        private val MORSE_CODE_MAP = hashMapOf(
            'A' to "01",
            'B' to "1000",
            'C' to "1010",
            'D' to "100",
            'E' to "0",
            'F' to "0010",
            'G' to "110",
            'H' to "0000",
            'I' to "00",
            'J' to "0111",
            'K' to "101",
            'L' to "0100",
            'M' to "11",
            'N' to "10",
            'O' to "111",
            'P' to "0110",
            'Q' to "1101",
            'R' to "010",
            'S' to "000",
            'T' to "1",
            'U' to "001",
            'V' to "0001",
            'W' to "011",
            'X' to "1001",
            'Y' to "1011",
            'Z' to "1100",
            // Numbers
            '0' to "11111",
            '1' to "01111",
            '2' to "00111",
            '3' to "00011",
            '4' to "00001",
            '5' to "00000",
            '6' to "10000",
            '7' to "11000",
            '8' to "11100",
            '9' to "11110",
            // Punctuation
            '.' to "010101",
            ',' to "110011",
            '?' to "001100",
            '\'' to "011110",
            '!' to "101011",
            '/' to "10010",
            '(' to "10110",
            ')' to "101101",
            '&' to "01000",
            ':' to "111000",
            ';' to "101010",
            '=' to "10001",
            '+' to "01010",
            '-' to "100001",
            '_' to "001101",
            '"' to "010010",
            '$' to "0001001",
            '@' to "011010"
        )

        private fun reverseMorseCodeMap(): HashMap<String, Char> {
            val map = HashMap<String, Char>()
            MORSE_CODE_MAP.forEach {
                map[it.value] = it.key
            }
            return map
        }

        private val REVERSE_MORSE_CODE_MAP = reverseMorseCodeMap()

        fun text2MorseCode(
            text: String, dit: Char = '.',
            dah: Char = '-',
            separator: Char = ' ',
            space: Char = '/'
        ): String {
            return if (text.isBlank()) {
                ""
            } else {
                val words = text.split(' ').filter { it.isNotBlank() }

                words.joinToString(space.toString()) {
                    it.mapIndexed { index, ch ->
                        MORSE_CODE_MAP[ch.toUpperCase()] ?: it.codePointAt(index).toString(2)
                    }
                        .joinToString(separator = separator.toString())
                        .replace('0', dit)
                        .replace('1', dah)
                }

            }
        }

        fun morseCode2Text(
            morseCode: String,
            dit: Char = '.',
            dah: Char = '-',
            separator: Char = ' ',
            space: Char = '/'
        ): String {
            return if (morseCode.isBlank()) {
                ""
            } else {
                morseCode.split(space)
                    .filter { it.isNotBlank() }
                    .joinToString(" ") { word ->
                        word.split(separator)
                            .filter { it.isNotBlank() }
                            .map {
                                val code = it.replace(dit, '0')
                                    .replace(dah, '1')
                                REVERSE_MORSE_CODE_MAP[code]
                                    ?: try {
                                        StringBuilder().appendCodePoint(code.toInt(2)).toString()
                                    } catch (e: IllegalArgumentException) {
                                        throw e
                                    }
                            }
                            .joinToString(separator = "")
                            .toLowerCase(Locale.ROOT)
                    }

//                morseCode.split(space)
//                    .filter { it.isNotBlank() }
//                    .map {
//                        val code = it.replace(dit, '0')
//                            .replace(dah, '1')
//                        REVERSE_MORSE_CODE_MAP[code]
//                            ?: try {
//                                StringBuilder().appendCodePoint(code.toInt(2)).toString()
//                            } catch (e: IllegalArgumentException) {
//                                throw e
//                            }
//                    }
//                    .joinToString(separator = "")
//                    .toLowerCase(Locale.ROOT)
            }
        }
    }
}