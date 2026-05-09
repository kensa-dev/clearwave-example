package com.clearwave.fields

import dev.kensa.kotest.testsupport.field.xml.XPathExpressionWrapper
import dev.kensa.kotest.testsupport.field.xml.XmlField
import dev.kensa.kotest.testsupport.field.xml.XmlListField
import dev.kensa.kotest.testsupport.field.xml.XmlStringField
import org.w3c.dom.Node
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathFactory

/**
 * XML field declarations for the FibreVision feasibility response.
 *
 * Each declaration captures an XPath plus a transform (where needed). Wrapping the compiled
 * expression in [XPathExpressionWrapper] keeps the path string introspectable by tooling.
 */
object FibreVisionResponseFields {

    val status = XmlStringField(compile("/FeasibilityResponse/Status"), "Status")

    val firstProfileType         = XmlStringField(compile("/FeasibilityResponse/Profiles/Profile[1]/Type"), "Profile Type")
    val firstProfileDownloadSpeed = XmlField(compile("/FeasibilityResponse/Profiles/Profile[1]/DownloadSpeed"), "Download Speed") { it.textContent.toInt() }

    val profileTypes = XmlListField(compile("/FeasibilityResponse/Profiles/Profile/Type"), "Profile Types") { it.textContent }

    val profileDescriptions = XmlListField(compile("/FeasibilityResponse/Profiles/Profile/Description"), "Profile Descriptions") { it.textContent }

    private fun compile(path: String): XPathExpression =
        XPathExpressionWrapper(XPathFactory.newInstance().newXPath().compile(path), path)
}
