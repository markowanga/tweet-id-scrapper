package pl.theliver.tweetidscrapper.infrastructure

import org.jsoup.nodes.Element

object JsoupUtils {

    fun selectFromElement(element: Element, selectors: List<String>): List<Element> {
        val foundElementsFromFirstSelector = element.select(selectors[0]).toList()
        return if (selectors.size == 1) {
            foundElementsFromFirstSelector
        } else {
            foundElementsFromFirstSelector.flatMap {
                selectFromElement(it, selectors.subList(1, selectors.size))
            }
        }
    }

}