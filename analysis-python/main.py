from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression
from datetime import datetime, timedelta

app = FastAPI()

# Modelo de entrada (igual ao objeto Transaction do Java)
class TransactionEntry(BaseModel):
    date: str
    amount: float
    type: str # INCOME ou EXPENSE

@app.post("/predict")
def predict_cashflow(history: List[TransactionEntry]):
    # 1. Converter JSON para DataFrame do Pandas
    df = pd.DataFrame([t.dict() for t in history])
    
    if df.empty:
        return {"error": "Sem dados históricos suficientes para prever."}

    # 2. Pré-processamento de Dados (Data Science Puro)
    # Converter string 'date' para datetime
    df['date'] = pd.to_datetime(df['date'])
    
    # Considerar apenas DESPESAS para prever fluxo de caixa negativo (exemplo)
    # Ou podemos prever saldo líquido. Vamos simplificar prevendo GASTOS.
    df_expenses = df[df['type'] == 'EXPENSE'].copy()
    
    if len(df_expenses) < 2:
        return {"message": "Preciso de pelo menos 2 despesas para traçar uma tendência."}

    # Transformar Data em "Dia Numérico" para o modelo matemático entender
    # Ex: Dia 1, Dia 5, Dia 10...
    df_expenses['day_numeric'] = (df_expenses['date'] - df_expenses['date'].min()).dt.days
    
    # 3. Treinar o Modelo (Machine Learning)
    X = df_expenses[['day_numeric']] # Features (Tempo)
    y = df_expenses['amount']        # Target (Valor gasto)
    
    model = LinearRegression()
    model.fit(X, y)
    
    # 4. Prever o Futuro (Próximo mês = +30 dias do último registro)
    last_day = df_expenses['day_numeric'].max()
    future_day = np.array([[last_day + 30]])
    
    predicted_expense = model.predict(future_day)[0]
    
    # Coeficiente angular (Se > 0, os gastos estão subindo. Se < 0, descendo)
    trend = "subindo" if model.coef_[0] > 0 else "descendo"

    return {
        "status": "success",
        "algorithm": "Linear Regression (Scikit-Learn)",
        "analysis": {
            "total_analyzed": len(df_expenses),
            "trend_direction": trend,
            "predicted_expense_next_month": round(float(predicted_expense), 2)
        }
    }
