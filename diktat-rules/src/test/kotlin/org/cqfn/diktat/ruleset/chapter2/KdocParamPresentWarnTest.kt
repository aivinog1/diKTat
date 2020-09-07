package org.cqfn.diktat.ruleset.chapter2

import com.pinterest.ktlint.core.LintError
import generated.WarningNames
import org.cqfn.diktat.ruleset.constants.Warnings.*
import org.cqfn.diktat.ruleset.rules.DIKTAT_RULE_SET_ID
import org.cqfn.diktat.ruleset.rules.kdoc.KdocMethods
import org.cqfn.diktat.util.lintMethod
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class KdocParamPresentWarnTest {

    private val ruleId = "$DIKTAT_RULE_SET_ID:kdoc-methods"

    @Test
    @Tag(WarningNames.KDOC_WITHOUT_PARAM_TAG)
    fun `check simple correct example`() {
        lintMethod(KdocMethods(),
                """
                    |/**
                    |* @param a - leftOffset
                    |*/
                    |fun foo(a: Int) {}
                """.trimMargin()
        )
    }

    @Test
    @Tags(Tag(WarningNames.KDOC_WITHOUT_PARAM_TAG), Tag(WarningNames.KDOC_WITHOUT_PARAM_TAG))
    fun `check wrong example with russian letter name`() {
        lintMethod(KdocMethods(),
                """
                    |/**
                    |* @param A - leftOffset
                    |* @param В - russian letter
                    |*/
                    |fun foo(a: Int, B: Int) {}
                """.trimMargin(),
                LintError(1, 1, ruleId, "${KDOC_WITHOUT_PARAM_TAG.warnText()} foo (a, B)", true),
                LintError(2, 3, ruleId, "${KDOC_WITHOUT_PARAM_TAG.warnText()} A param isn't define in function"),
                LintError(3, 3, ruleId, "${KDOC_WITHOUT_PARAM_TAG.warnText()} В param isn't define in function")
        )
    }

    @Test
    @Tag(WarningNames.KDOC_WITHOUT_PARAM_TAG)
    fun `check wrong example without param in fun`() {
        lintMethod(KdocMethods(),
                """
                    |/**
                    |* @param A - leftOffset
                    |*/
                    |fun foo() {}
                """.trimMargin(),
                LintError(2, 3, ruleId, "${KDOC_WITHOUT_PARAM_TAG.warnText()} A param isn't define in function")
        )
    }

    @Test
    @Tag(WarningNames.KDOC_WITHOUT_PARAM_TAG)
    fun `check empty param`() {
        lintMethod(KdocMethods(),
                """
                    |/**
                    |* @param
                    |*/
                    |fun foo() {}
                    |
                    |/**
                    |* @param
                    |*/
                    |fun foo (a: Int) {}
                """.trimMargin(),
                LintError(6, 1, ruleId, "${KDOC_WITHOUT_PARAM_TAG.warnText()} foo (a)", true)
        )
    }

    @Test
    @Tag(WarningNames.KDOC_WITHOUT_PARAM_TAG)
    fun `check different order`() {
        lintMethod(KdocMethods(),
                """
                    |/**
                    |* @param a - qwe
                    |* @param b - qwe
                    |*/
                    |fun foo(b: Int, a: Int) {}
                """.trimMargin()
        )
    }

    @Test
    @Tags(Tag(WarningNames.KDOC_WITHOUT_PARAM_TAG), Tag(WarningNames.MISSING_KDOC_ON_FUNCTION))
    fun `check without kdoc and fun `() {
        lintMethod(KdocMethods(),
                """
                    |fun foo(b: Int, a: Int) {}
                    |
                    |/**
                    |* @param a - qwe
                    |*/
                """.trimMargin(),
                LintError(1, 1, ruleId, "${KDOC_WITHOUT_PARAM_TAG.warnText()} foo (b, a)", true),
                LintError(1, 1, ruleId, "${MISSING_KDOC_ON_FUNCTION.warnText()} foo", true)
        )
    }
}