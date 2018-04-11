'use strict';

/******************************************************************************************

Expenses controller

******************************************************************************************/

var app = angular.module('expenses.controller', []);

app.controller('ctrlExpenses', [
	'$rootScope',
	'$scope',
	'config',
	'restalchemy',
	function ExpensesCtrl($rootScope, $scope, $config, $restalchemy) {
		// Update the headings
		$rootScope.mainTitle = 'Expenses';
		$rootScope.mainHeading = 'Expenses';

		// Update the tab sections
		$rootScope.selectTabSection('expenses', 0);

		var restExpenses = $restalchemy.init({ root: $config.apiroot }).at('expenses');

		$scope.dateOptions = {
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd/mm/yy',
		};

		var loadExpenses = function() {
			// Retrieve a list of expenses via REST
			restExpenses.get().then(function(expenses) {
				$scope.expenses = expenses;
			});
		};

		$scope.saveExpense = function() {
			if ($scope.expensesform.$valid) {
				// Post the expense via REST

				restExpenses.post($scope.newExpense).then(function() {
					// Reload new expenses list
					loadExpenses();
				});
			}
		};

		$scope.$watchGroup(['newExpense.amount'], function() {
			if ($scope.newExpense.amount !== undefined) {
				var strAmount = $scope.newExpense.amount;
				var s = strAmount.split(/[EUR]| € |\u20AC/gi);

				if (s.length > 1) {
					//remove  EUR from amount if any
					var theAmount = parseFloat(s.join('').trim());

					s = theAmount;
				}
			}
			$scope.newExpense.vat = (s - s / 1.2).toFixed(2);
		});
		$scope.clearExpense = function() {
			$scope.newExpense = {};
		};

		// Initialise scope variables
		loadExpenses();
		$scope.clearExpense();
	},
]);
