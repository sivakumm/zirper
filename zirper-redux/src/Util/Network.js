
const doFetch = async (url, options, actionType, errorText, dispatch) => {
    try {
        dispatch({ type: 'LOADING', value: true });
        const response = await fetch(url, { ...options, headers: { 'Content-Type': 'application/json' } })
        if (response.ok) {
            dispatch({ type: 'LOADING', value: false });
            dispatch({ type: 'ERROR', value: '' });
            if (response.status !== 204){
				dispatch({ type: actionType, value: await response.json() });
            }
        } else {
            throw new Error(`${errorText} (${response.status} - ${response.statusText})`);
        }
    } catch (error) {
        console.error(error);
        dispatch({ type: 'LOADING', value: false });
        dispatch({ type: 'ERROR', value: error.message });
    }
};

export default doFetch;