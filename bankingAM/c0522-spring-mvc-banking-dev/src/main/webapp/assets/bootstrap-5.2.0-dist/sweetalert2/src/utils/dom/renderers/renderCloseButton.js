import * as dom from 'sweetalert2/src/utils/dom'

/**
 * @param {SweetAlert2} instance
 * @param {SweetAlertOptions} params
 */
export const renderCloseButton = (instance, params) => {
  const closeButton = dom.getCloseButton()

  dom.setInnerHtml(closeButton, params.closeButtonHtml)

  // Custom class
  dom.applyCustomClass(closeButton, params, 'closeButton')

  dom.toggle(closeButton, params.showCloseButton)
  closeButton.setAttribute('aria-label', params.closeButtonAriaLabel)
}
