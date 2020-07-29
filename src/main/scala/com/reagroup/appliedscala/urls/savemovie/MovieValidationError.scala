package com.reagroup.appliedscala.urls.savemovie

import com.reagroup.appliedscala.urls.fetchenrichedmovie.Metascore
import io.circe.Encoder
import io.circe.Json
import io.circe.syntax._

sealed trait MovieValidationError

case object MovieNameTooShort extends MovieValidationError

case object MovieSynopsisTooShort extends MovieValidationError

object MovieValidationError {

  /**
    * Write a function that turns an `MovieValidationError` to a `String`.
    * This will be used in our `Encoder`.
    *
    *
    * `MovieNameTooShort` -> "MOVIE_NAME_TOO_SHORT"
    * `MovieSynopsisTooShort` -> "MOVIE_SYNOPSIS_TOO_SHORT"
    *
    * Hint: Use pattern matching
    */
  def show(error: MovieValidationError): String =
    error match {
      case MovieNameTooShort => "MOVIE_NAME_TOO_SHORT"
      case MovieSynopsisTooShort => "MOVIE_SYNOPSIS_TOO_SHORT"
      case _ => "UNKNOWN"
    }

  /**
    * Add an Encoder instance here
    *
    * We want the resulting Json to look like this:
    *
    * {
    *   "error": "MOVIE_NAME_TOO_SHORT"
    * }
    *
    * Hint: You don't want to use `deriveEncoder` here
    */

  implicit val encoder: Encoder[MovieValidationError] = Encoder.instance(error => Json.obj("error" := show(error)))
}
