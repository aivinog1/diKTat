package org.cqfn.diktat.util

import com.pinterest.ktlint.core.Rule
import org.cqfn.diktat.common.config.rules.RulesConfig
import org.cqfn.diktat.ruleset.constants.Warnings
import org.cqfn.diktat.test.framework.processing.TestComparatorUnit
import org.junit.jupiter.api.Assertions

open class FixTestBase(private val resourceFilePath: String,
                       private val rules: List<Rule>,
                       rulesConfigList: List<RulesConfig> = emptyList()
) {
    private val testComparatorUnit = TestComparatorUnit(resourceFilePath) { text, fileName ->
        // adding dummy warnings to a rulesConfigList to have proper tests and debug
        val dummyWarning = RulesConfig(Warnings.STRING_CONCATENATION.name, true, mapOf())
        rules.format(text, fileName, rulesConfigList)
    }

    protected fun fixAndCompare(expectedPath: String, testPath: String) {
        Assertions.assertTrue(
                testComparatorUnit
                        .compareFilesFromResources(expectedPath, testPath)
        )
    }

    protected fun fixAndCompare(expectedPath: String, testPath: String, overrideRulesConfigList: List<RulesConfig>) {
        val testComparatorUnit = TestComparatorUnit(resourceFilePath) { text, fileName ->
            rules.format(text, fileName, overrideRulesConfigList)
        }
        Assertions.assertTrue(
                testComparatorUnit
                        .compareFilesFromResources(expectedPath, testPath)
        )
    }
}
