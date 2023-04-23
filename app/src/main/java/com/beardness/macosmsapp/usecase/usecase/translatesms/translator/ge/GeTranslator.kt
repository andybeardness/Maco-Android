package com.beardness.macosmsapp.usecase.usecase.translatesms.translator.ge

import com.beardness.macosmsapp.usecase.usecase.translatesms.translator.BaseTranslatorProtocol
import javax.inject.Inject

class GeTranslator @Inject constructor(
    private val autoTranslator: BaseTranslatorProtocol,
) : BaseTranslatorProtocol {

    private val translateMapRaw =
        """
            a    -> ა 
            k    -> კ 
            t    -> ტ 
            dz   -> ძ 
            b    -> ბ 
            l    -> ლ 
            u    -> უ 
            ts   -> წ 
            g    -> გ 
            m    -> მ 
            p    -> ფ 
            f    -> ფ 
            ch   -> ჭ 
            d    -> დ 
            n    -> ნ 
            k    -> ქ 
            kh   -> ხ 
            x    -> ხ 
            e    -> ე 
            o    -> ო 
            gh   -> ღ 
            j    -> ჯ 
            v    -> ვ 
            w    -> ვ 
            p    -> პ 
            q    -> ყ 
            h    -> ჰ 
            z    -> ზ 
            zh   -> ჟ 
            sh   -> შ 
            t    -> თ 
            r    -> რ 
            ch   -> ჩ 
            i    -> ი 
            s    -> ს 
            ts   -> ც 
            c    -> ც 
        """.trimIndent()

    private val translateMap =
        translateMapRaw
            .split("\n")
            .map { line -> line.split(" -> ") }
            .map { kv ->
                val key = kv.getOrNull(index = 0)?.trim() ?: ""
                val value = kv.getOrNull(index = 1)?.trim() ?: ""

                key to value
            }
            .sortedByDescending { pair -> pair.first.length }


    override suspend fun translate(text: String): String? {
        val formatted = formatGe(text = text)
        return autoTranslator.translate(text = formatted)
    }

    private fun formatGe(text: String): String {
        var formatted = text

        translateMap.forEach { pair ->
            formatted = formatted.replace(oldValue = pair.first, newValue = pair.second)
        }

        return formatted
    }
}