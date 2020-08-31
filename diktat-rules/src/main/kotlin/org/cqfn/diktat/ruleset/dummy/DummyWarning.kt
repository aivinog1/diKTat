package org.cqfn.diktat.ruleset.dummy

import com.pinterest.ktlint.core.KtLint
import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.ElementType.FILE
import org.cqfn.diktat.ruleset.utils.*
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import java.lang.annotation.ElementType

/**
 * Dummy warning used for testing and debug purposes.
 * Can be used in manual testing.
 */
class DummyWarning : Rule("dummy-warning") {
    override fun visit(node: ASTNode,
                       autoCorrect: Boolean,
                       params: KtLint.Params,
                       emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit) {
    }
}
