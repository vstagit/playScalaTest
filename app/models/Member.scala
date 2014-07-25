package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Member(id: Long, name: String, email: String)

object Member {

  def getAll:List[Member] = {
    DB.withConnection { implicit connection =>
      SQL("""select * from member""").as(simple *)
    }
  }

  def add(name: String, email: String) {
    DB.withConnection { implicit connection =>
      SQL("""insert into member (name, email) values ({name},{email})""").on(
        'name -> name,
        'email -> email).executeUpdate()
    }
  }

  def remove(name: String, email:String) {
    DB.withConnection { implicit connection =>
      SQL("""delete from member where name = {name} and email = {email}""").on(
      'name -> name,
      'email -> email).executeUpdate()
    }
  }

  def simple() = {
      get[Long]("id") ~
      get[String]("name") ~
      get[String]("email") map {
      case id ~ name ~ email => Member(id, name, email)
    }
  }

}
