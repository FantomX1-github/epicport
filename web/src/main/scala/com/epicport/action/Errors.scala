package com.epicport.action

import xitrum.annotation.Error404
import xitrum.annotation.Error500
import io.netty.handler.codec.http.HttpResponseStatus

trait ErrorAction extends DefaultAction {
  def title = t("html_error_title")
  def keywords = ""
}

@Error404
class NotFoundError extends DefaultLayout with ErrorAction {
  def description = t("html_not_found_description")
  
  def execute() {
    response.setStatus(HttpResponseStatus.NOT_FOUND)
    respondView()
  }
}

@Error500
class ServerError extends DefaultLayout with ErrorAction {
  def description = t("html_server_error_description")
  
  def execute() {
    response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR)
    respondView()
  }
}
