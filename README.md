###MD-JD2-74-21 Веремей Д.Ю. ###

####Структура проекта: 
#####academy
      |____src\by\academy
      |              |_____bean
      |              |      |__LoginationInfo
      |              |      |__News
      |              |      |__RegistrationInfo
      |              |      |__User
      |              |
      |              |_____Controller
      |              |      |__Command
      |              |          |   |__impl
      |              |          |        |__Edit
      |              |          |        |__DeleteNews
      |              |          |        |__ToMainPage
      |              |          |
      |              |          |__Command
      |              |          |__CommandName
      |              |          |__CommandProvider
      |              |
      |              |_____DAO
      |              |      ||__impl
      |              |      |     |__MYSQLDriverLoader
      |              |      |     |__SQLNewsDAO
      |              |      |     |__SQLUserDAO
      |              |      |
      |              |      |__DAOException
      |              |      |__DAOProvider
      |              |      |__DABDriverLoadingException
      |              |      |__NewsDAO
      |              |      |__UserDAO      
      |              |
      |              |_____Service
      |                       |  |__impl
      |                       |       |__NewsServiceImpl
      |                       |       |__UserServiceImpl
      |                       |
      |                       |__