package com.study.myapplication.bean


class BorrowProductBean {
    var productName: String? = null
    var productId: String? = null
    var arrovedBank: String? = null
    var arrovedBankCard: String? = null
    var replanRepayTime: String? = null
    var productDays: String? = null
    var moneyList: List<MoneyList>? = null
    var isClicked = true
    var isEndable = true

    class MoneyList {
        var brMoney: String? = null
        var instMoney: String? = null
        var pamentMoney: String? = null
        var arrovedMoney: String? = null
        var accountManagementMoney: String? = null
    }
}
