import React from 'react';
import {Routes, Route} from 'react-router-dom'
import ShowBalanceAdmin from '../showBalance/showBalanceAdmin';
import SuspendAccount from './suspendAccount';
import TransactAdmin from './transactAdmin';
import UpdateDetails from './updateDetailsAdmin';
import ActivateAccount from './activateAccount';
import NotFoundPage from '../NotFoundPage';
const AdminContent = () =>{
    const style = {
        position: 'absolute',
        left: '50%'
    }
    return(
        <div>
            <Routes>
                <Route path='/' element={<div style={style}>Hello Admin!</div>} />
                <Route path='/showBalanceAdmin' element={<ShowBalanceAdmin/>}/>
                <Route path='/transact' element={<TransactAdmin/>}/>
                <Route path='/update' element={<UpdateDetails/>}/>
                <Route path='/suspendAccount' element={<SuspendAccount/>}/>
                <Route path='/activateAccount' element={<ActivateAccount/>}/>
                <Route path="*" element={<NotFoundPage home="/welcomeAdmin"/>} />
            </Routes>
        </div>
    )
}

export default AdminContent;