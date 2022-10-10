import * as dom from 'sweetalert2/src/utils/dom'
import privateProps from 'sweetalert2/src/privateProps.js'

/**
 * Gets the input DOM node, this method works with input parameter.
 * @returns {HTMLElement | null}
 */
export function getInput(instance) {
  const innerParams = privateProps.innerParams.get(instance || this)
  const domCache = privateProps.domCache.get(instance || this)
  if (!domCache) {
    return null
  }
  return dom.getInput(domCache.popup, innerParams.input)
}
