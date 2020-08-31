package org.cqfn.diktat.ruleset.chapter3

import generated.WarningNames
import org.cqfn.diktat.common.config.rules.RulesConfig
import org.cqfn.diktat.ruleset.constants.Warnings
import org.cqfn.diktat.ruleset.dummy.DummyWarning
import org.cqfn.diktat.ruleset.rules.StringConcatenationRule
import org.cqfn.diktat.util.FixTestBase
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class StringConcatenationRuleFixTest : FixTestBase("test/chapter3/strings", listOf(StringConcatenationRule(), DummyWarning()),
        listOf(
                RulesConfig(Warnings.STRING_CONCATENATION.name, true, mapOf())
        )
) {
    @Test
    @Disabled("ignored due to not all cases supported now")
    @Tag(WarningNames.STRING_CONCATENATION)
    fun `fixing string concatenation`() {
        fixAndCompare("StringConcatenationExpected.kt", "StringConcatenationTest.kt")
    }
}
