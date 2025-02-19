project {
  // Стартовый сценарий
  state("start") {
    activators {
      regex("привет|здравствуйте|начать")
    }
    action {
      reactions.say("Здравствуйте! Вы позвонили в [Название компании]. Чем могу помочь?")
      reactions.buttons("Проверить статус заявки", "Получить информацию о продукте", "Связаться с оператором")
    }
  }

  // Проверка статуса заявки
  state("check_status") {
    activators {
      intent("Проверить статус заявки")
    }
    action {
      reactions.say("Пожалуйста, продиктуйте номер вашей заявки.")
      reactions.go("wait_for_number")
    }
  }
  
  state("wait_for_number") {
    activators {
      regexp("\\d+") // Ожидаем номер заявки
    }
    action {
      reactions.say("Спасибо! Проверяю статус вашей заявки...")
      reactions.say("Ваша заявка находится в обработке. Ожидайте, скоро с вами свяжется специалист.")
    }
  }

  // Информация о продукте
  state("product_info") {
    activators {
      intent("Получить информацию о продукте")
    }
    action {
      reactions.say("Наш продукт [Название продукта] позволяет вам ... (описание). Подробнее вы можете узнать на нашем сайте.")
    }
  }

  // Связь с оператором
  state("connect_operator") {
    activators {
      intent("Связаться с оператором")
    }
    action {
      reactions.say("Переключаю вас на оператора. Пожалуйста, оставайтесь на линии.")
      reactions.callcenter.connect()
    }
  }

  // Завершение разговора
  state("end") {
    action {
      reactions.say("Спасибо за обращение! Хорошего дня!")
    }
  }
}