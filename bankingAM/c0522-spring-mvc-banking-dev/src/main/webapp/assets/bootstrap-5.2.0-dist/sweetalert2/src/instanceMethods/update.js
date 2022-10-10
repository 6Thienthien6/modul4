import * as dom from 'sweetalert2/src/utils/dom'
import { warn } from 'sweetalert2/src/utils/utils.js'
import privateProps from 'sweetalert2/src/privateProps.js'
import { isUpdatableParameter } from 'sweetalert2/src/utils/params.js'

/**
 * Updates popup parameters.
 */
export function update(params) {
  const popup = dom.getPopup()
  const innerParams = privateProps.innerParams.get(this)

  if (!popup || dom.hasClass(popup, innerParams.hideClass.popup)) {
    return warn(
      `You're trying to update the closed or closing popup, that won't work. Use the update() method in preConfirm parameter or show a new popup.`
    )
  }

  const validUpdatableParams = filterValidParams(params)

  const updatedParams = Object.assign({}, innerParams, validUpdatableParams)

  dom.render(this, updatedParams)

  privateProps.innerParams.set(this, updatedParams)
  Object.defineProperties(this, {
    params: {
      value: Object.assign({}, this.params, params),
      writable: false,
      enumerable: true,
    },
  })
}

const filterValidParams = (params) => {
  const validUpdatableParams = {}
  Object.keys(params).forEach((param) => {
    if (isUpdatableParameter(param)) {
      validUpdatableParams[param] = params[param]
    } else {
      warn(`Invalid parameter to update: ${param}`)
    }
  })
  return validUpdatableParams
}
